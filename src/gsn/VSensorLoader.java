package gsn;

import gsn.beans.AddressBean;
import gsn.beans.DataField;
import gsn.beans.InputStream;
import gsn.beans.Modifications;
import gsn.beans.StreamSource;
import gsn.beans.VSensorConfig;
import gsn.msr.sensormap.TestPublicToMSR;
import gsn.msr.sensormap.LoginToMSRSense;
import gsn.storage.PoolIsFullException;
import gsn.storage.StorageManager;
import gsn.wrappers.AbstractWrapper;
import gsn.wrappers.WrappersUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.rmi.RemoteException;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.jibx.runtime.JiBXException;

public class VSensorLoader extends Thread {
  
  
  public static final String                                     VSENSOR_POOL                        = "VSENSOR-POOL";
  
  public static final String                                     STREAM_SOURCE                       = "STREAM-SOURCE";
  
  public static final String                                     INPUT_STREAM                        = "INPUT-STREAM";
  
  private static transient Logger                                logger                              = Logger.getLogger ( VSensorLoader.class );
  
  /**
   * Mapping between the AddressBean and DataSources
   */
  private  final HashMap < AddressBean , AbstractWrapper > activeWrappers                   = new HashMap < AddressBean ,  AbstractWrapper >( );
  
  private StorageManager                                         sm                      = StorageManager.getInstance ( );
  
  private String                                                 pluginsDir;
  
  private boolean                                                isActive                              = true;
  
  private static int                                             VSENSOR_LOADER_THREAD_COUNTER       = 0;
  
  
  public VSensorLoader() {
    
  }
  public VSensorLoader ( String pluginsPath ) {
    this.pluginsDir = pluginsPath;
    Thread thread = new Thread ( this );
    thread.setName ( "VSensorLoader-Thread" + VSENSOR_LOADER_THREAD_COUNTER++ );
    thread.start ( );
  }
  
  public void run ( ) {
    if ( sm == null ) {
      logger.fatal ( "The Storage Manager shouldn't be null, possible a BUG." );
      return;
    }
    while ( isActive ) {
      try {
        loadPlugin ( );
      } catch ( Exception e ) {
        logger.error ( e.getMessage ( ) , e );
      }
    }
  }
  
  public void loadPlugin ( ) throws SQLException , JiBXException {
    Modifications modifications = getUpdateStatus ( pluginsDir );
    ArrayList < VSensorConfig > removeIt = modifications.getRemove ( );
    ArrayList<VSensorConfig> addIt = modifications.getAdd();
    for ( VSensorConfig configFile : removeIt ) {
      logger.warn ( new StringBuilder ( ).append ( "removing : " ).append ( configFile.getName ( ) ).toString ( ) );
      VirtualSensorPool sensorInstance = Mappings.getVSensorInstanceByFileName ( configFile.getFileName ( ) );
      Mappings.removeFilename ( configFile.getFileName ( ) );
      removeAllVSResources ( sensorInstance );
    }
    try {
      Thread.sleep ( 3000 );
    } catch ( InterruptedException e ) {
      logger.error ( e.getMessage ( ) , e );
    }finally {
      if ( this.isActive == false ) return;
    }
    
    for ( VSensorConfig vs : addIt ) {
      if (!isVirtualSensorValid(vs))
        continue ;
      
      VirtualSensorPool pool = new VirtualSensorPool ( vs );
      try {
        if ( createInputStreams (  pool ) == false ) {
          logger.error ( "loading the >" + vs.getName() + "< virtual sensor is stoped due to error(s) in preparing the input streams." );
          continue;
        }
      } catch (InstantiationException e2) {
        logger.error(e2.getMessage(),e2);
      } catch (IllegalAccessException e2) {
        logger.error(e2.getMessage(),e2);
      }
      try {
        if (!sm.tableExists( vs.getName ( ) , vs.getOutputStructure ( ) ))
          sm.executeCreateTable ( vs.getName ( ) , vs.getOutputStructure ( ),pool.getConfig().getIsTimeStampUnique() );
        else
          logger.info("Reusing the existing "+vs.getName()+" table.");
      } catch ( SQLException e ) {
        if ( e.getMessage ( ).toLowerCase ( ).contains ( "table already exists" ) ) {
          logger.error ( e.getMessage ( ) );
          if ( logger.isInfoEnabled ( ) ) logger.info ( e.getMessage ( ) , e );
          logger.error ( new StringBuilder ( ).append ( "Loading the virtual sensor specified in the file : " ).append ( vs.getFileName ( ) ).append ( " failed" ).toString ( ) );
          logger.error ( new StringBuilder ( ).append ( "The table : " ).append ( vs.getName ( ) ).append ( " is exists in the database specified in :" ).append (
              Main.getContainerConfig ( ).getContainerFileName ( ) ).append ( "." ).toString ( ) );
          logger.error ( "Solutions : " );
          logger.error ( new StringBuilder ( ).append ( "1. Change the virtual sensor name, in the : " ).append ( vs.getFileName ( ) ).toString ( ) );
          logger.error ( new StringBuilder ( ).append ( "2. Change the URL of the database in " ).append ( Main.getContainerConfig ( ).getContainerFileName ( ) ).append (
          " and choose another database." ).toString ( ) );
          logger.error ( new StringBuilder ( ).append ( "3. Rename/Move the table with the name : " ).append ( Main.getContainerConfig ( ).getContainerFileName ( ) ).append ( " in the database." )
              .toString ( ) );
          logger.error ( new StringBuilder ( ).append ( "4. Change the overwrite-tables=\"true\" (be careful, this will overwrite all the data previously saved in " ).append (
              vs.getName ( ) ).append ( " table )" ).toString ( ) );
        } else {
          logger.error ( e.getMessage ( ) , e );
        }
        continue;
      }
      logger.warn ( new StringBuilder ( "adding : " ).append ( vs.getName() ).append ( " virtual sensor[" ).append ( vs.getFileName ( ) ).append ( "]" ).toString ( ) );
      Mappings.addVSensorInstance ( pool );
      try {
        pool.start ( );
        if (pool.getConfig().getPublishToSensorMap())
          register_to_microsoft_sensor_map(pool.getConfig());
      } catch ( PoolIsFullException e1 ) {
        logger.error ( "Creating the virtual sensor >" + vs.getName ( ) + "< failed." , e1 );
        continue;
      } catch ( VirtualSensorInitializationFailedException e1 ) {
        logger.error ( "Creating the virtual sensor >" + vs.getName ( ) + "< failed." , e1 );
        continue;
      }
    }
  }
  
  private void register_to_microsoft_sensor_map(VSensorConfig config) {
    logger.warn("Virtual Sensor: "+config.getName()+" wants to publish its data to SensorMap.");
    Double lat = config.getLatitude();
    Double lng = config.getLongitude();
    Double alt = config.getAltitude();
    if (lat==null || lng ==null || alt==null) {
      logger.warn("Err, Virtual Sensor cannot be registered to sensor map as it doesn't have latitude, longitude and/or altitude addressing attributes.");
      return;
    }
    HashMap<String,String> params = Main.getContainerConfig().getMsrMap();
    String userName = params.get("user");
    String password = params.get("password");
    String host = params.get("host");
    if (userName==null||password==null||host==null) {
      logger.warn("user and/or password and/or host parameters are missing from the gsn.xml file from the microsoft-research-sensormap path.");
      logger.warn("registeration to microsoft research sensormap failed.");
      logger.warn("Not that the host has the format of machineName:PortNumber , missing PortNumber implies port 80.");
      return;
    }
    try {
        LoginToMSRSense.register_sensor(userName, password, config, host);
      //TestPublicToMSR.register_to_sensor_map(userName, password, host, Main.getContainerConfig(), config);
    } catch (RemoteException e) {
      logger.error(e.getMessage(),e);
    } catch (IOException e) {
      logger.error(e.getMessage(),e);
    }
  }

  private void unregister_from_microsoft_sensor_map(VSensorConfig config) {
    logger.warn("Virtual Sensor: "+config.getName()+" wants to unregister from SensorMap.");

    HashMap<String,String> params = Main.getContainerConfig().getMsrMap();
    String userName = params.get("user");
    String password = params.get("password");

    if (userName==null||password==null) {
      logger.warn("user and/or password parameters are missing from the gsn.xml file from the microsoft-research-sensormap path.");
      logger.warn("unregistering from microsoft research sensormap failed.");
      return;
    }
    try {
        LoginToMSRSense.delete_sensor(userName, password, config);
    } catch (RemoteException e) {
      logger.error(e.getMessage(),e);
    } catch (IOException e) {
      logger.error(e.getMessage(),e);
    }
  }
  
  public boolean isVirtualSensorValid(VSensorConfig configuration) {
    for ( InputStream is : configuration.getInputStreams ( ) ) {
      if ( !is.validate ( ) ) {
        logger.error ( new StringBuilder ( ).append ( "Adding the virtual sensor specified in " ).append ( configuration.getFileName ( ) ).append ( " failed because of one or more problems in configuration file." )
            .toString ( ) );
        logger.error ( new StringBuilder ( ).append ( "Please check the file and try again" ).toString ( ) );
        return false;
      }
    }
    String vsName = configuration.getName ( );
    if ( Mappings.getVSensorConfig ( vsName ) != null ) {
      logger.error ( new StringBuilder ( ).append ( "Adding the virtual sensor specified in " ).append ( configuration.getFileName ( ) ).append ( " failed because the virtual sensor name used by " )
          .append ( configuration.getFileName ( ) ).append ( " is already used by : " ).append ( Mappings.getVSensorConfig ( vsName ).getFileName ( ) ).toString ( ) );
      logger.error ( "Note that the virtual sensor name is case insensitive and all the spaces in it's name will be removed automatically." );
      return false;
    }
    
    
    
    if ( !isValidJavaIdentifier( vsName ) ) {
      logger.error ( new StringBuilder ( ).append ( "Adding the virtual sensor specified in " ).append ( configuration.getFileName ( ) ).append (
      " failed because the virtual sensor name is not following the requirements : " ).toString ( ) );
      logger.error ( "The virtual sensor name is case insensitive and all the spaces in it's name will be removed automatically." );
      logger.error ( "That the name of the virutal sensor should starting by alphabetical character and they can contain numerical characters afterwards." );
      return false;
    }
    return true;
  }
  
  static protected boolean isValidJavaIdentifier(final String name) {
    boolean valid = false;
    while (true) {
      if (false == Character.isJavaIdentifierStart(name.charAt(0))) 
        break;
      valid = true;
      final int count = name.length();
      for (int i = 1; i < count; i++) {
        if (false == Character.isJavaIdentifierPart(name.charAt(i))) {
          valid = false;
          break;
        }
      }
      break;
    }
    return valid;
  }

  public void removeAllVSResources ( VirtualSensorPool pool ) {
    VSensorConfig config = pool.getConfig ( );
    pool.closePool ( );
    final String vsensorName = config.getName ( );
    if ( logger.isInfoEnabled ( ) ) logger.info ( new StringBuilder ( ).append ( "Releasing previously used resources used by [" ).append ( vsensorName ).append ( "]." ).toString ( ) );
    for ( InputStream inputStream : config.getInputStreams ( ) ) {
      for ( StreamSource streamSource : inputStream.getSources ( ) ) 
        releaseStreamSource(streamSource);
      inputStream.finalize ( );
    }
    // sm.renameTable(vsensorName,vsensorName+"Before"+System.currentTimeMillis());
    Mappings.getContainer ( ).removeAllResourcesAssociatedWithVSName ( vsensorName );
    if (pool.getConfig().getPublishToSensorMap())
          unregister_from_microsoft_sensor_map(pool.getConfig());
//  this.sm.dropTable ( config.getName ( ) );
  }
  
  public void releaseStreamSource(StreamSource streamSource) {
    final AbstractWrapper wrapper = streamSource.getWrapper ( );
    streamSource.getInputStream().getRenamingMapping().remove(streamSource.getAlias());
    try {
      wrapper.removeListener(streamSource);
    } catch (SQLException e) {
      logger.error(e.getMessage(),e);
      logger.error("Release the resources failed !");
    }
    if ( !wrapper.isActive()) {//This stream source is the only listener
      activeWrappers.remove ( wrapper.getActiveAddressBean ( ) );
      Mappings.getContainer ( ).removeRemoteStreamSource ( wrapper.getDBAlias() );
    }
  }
  
  public static Modifications getUpdateStatus ( String virtualSensorsPath ) {
    ArrayList< String > remove = new ArrayList<String> ();
    ArrayList< String > add = new ArrayList<String> ();
    
    String [ ] previous = Mappings.getAllKnownFileName ( );
    FileFilter filter = new FileFilter ( ) {
      
      public boolean accept ( File file ) {
        if ( !file.isDirectory ( ) && file.getName ( ).endsWith ( ".xml" ) && !file.getName ( ).startsWith ( "." ) ) return true;
        return false;
      }
    };
    File files[] = new File ( virtualSensorsPath ).listFiles ( filter );
    // --- preparing the remove list
    // Removing those in the previous which are not existing the new files
    // or modified.
    main : for ( String pre : previous ) {
      for ( File curr : files )
        if ( pre.equals ( curr.getAbsolutePath ( ) ) && ( Mappings.getLastModifiedTime ( pre ) == curr.lastModified ( ) ) ) continue main;
      remove.add ( pre );
    }
    // ---adding the new files to the Add List a new file should added if
    //
    // 1. it's just deployed.
    // 2. it's modification time changed.
    
    main : for ( File cur : files ) {
      for ( String pre : previous )
        if ( cur.getAbsolutePath ( ).equals ( pre ) && ( cur.lastModified ( ) == Mappings.getLastModifiedTime ( pre ) ) ) continue main;
      add.add ( cur.getAbsolutePath ( ) );
    }
    Modifications result = new Modifications ( add , remove );
    return result;
  }
  
  /**
   * The properties file contains information on wrappers for stream sources.
   * FIXME : The body of CreateInputStreams is incomplete b/c in the case of an
   * error it should remove the resources.
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   */
  public boolean createInputStreams ( VirtualSensorPool pool ) throws InstantiationException, IllegalAccessException {
    if ( logger.isDebugEnabled ( ) ) logger.debug ( new StringBuilder ( ).append ( "Preparing input streams for: " ).append ( pool.getConfig().getName ( ) ).toString ( ) );
    if ( pool.getConfig().getInputStreams ( ).size ( ) == 0 ) logger.warn ( new StringBuilder ( "There is no input streams defined for *" ).append ( pool.getConfig().getName ( ) ).append ( "*" ).toString ( ) );
    for ( Iterator < InputStream > inputStreamIterator = pool.getConfig().getInputStreams ( ).iterator ( ) ; inputStreamIterator.hasNext ( ) ; ) {
      InputStream inputStream = inputStreamIterator.next ( );
      for ( StreamSource  dataSouce : inputStream.getSources ( )) {
        if ( prepareStreamSource ( pool.getConfig(),inputStream , dataSouce ) == false ) return false;
        // TODO if one stream source fails all the resources used by other successfuly initialized stream sources
        // for this input stream should be released.
      }
      inputStream.setPool (pool );
    }
    return true;
  }
  /**
   * Tries to find a wrapper first from the active wrappers or instantiates a new one and puts it in the cache.
   * @param addressBean
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   * FIXME: COPIED_FOR_SAFE_STOAGE
   */
  public AbstractWrapper findWrapper(AddressBean addressBean) throws InstantiationException, IllegalAccessException {
    AbstractWrapper wrapper = activeWrappers.get ( addressBean );
    if ( wrapper == null ) {
      if ( Main.getInstance().getWrapperClass ( addressBean.getWrapper ( ) ) == null ) {
        logger.error ( "The wrapper >" + addressBean.getWrapper ( ) + "< is not defined in the >" + WrappersUtil.DEFAULT_WRAPPER_PROPERTIES_FILE + "< file." );
        return null;
      }
      wrapper = ( AbstractWrapper ) Main.getInstance().getWrapperClass ( addressBean.getWrapper ( ) ).newInstance ( );
      wrapper.setActiveAddressBean ( addressBean );
      boolean initializationResult = wrapper.initialize (  );
      if ( initializationResult == false )
        return null;
      try {
        logger.debug("Wrapper name: "+wrapper.getWrapperName()+ " -- view name "+ wrapper.getDBAliasInStr());
        if (!sm.tableExists(wrapper.getDBAliasInStr(),wrapper.getOutputFormat()))
          sm.executeCreateTable ( wrapper.getDBAliasInStr ( ) , wrapper.getOutputFormat ( ),wrapper.isTimeStampUnique() );
      } catch ( SQLException e ) {
        logger.error ( e.getMessage ( ) , e );
        return null;
      }
      wrapper.start ( );
      activeWrappers.put ( wrapper.getActiveAddressBean() , wrapper );
    }else 
      if (logger.isDebugEnabled())
        logger.debug("Existing wrapper found and reused. [name :"+addressBean.getWrapper() );
    return wrapper;
  }
  public boolean prepareStreamSource ( VSensorConfig vsensorConfig,InputStream inputStream , StreamSource streamSource  ) throws InstantiationException, IllegalAccessException {
    streamSource.setInputStream(inputStream);
    AbstractWrapper wrapper = null;
    for ( AddressBean addressBean : streamSource.getAddressing ( ) ) {
      addressBean.setInputStreamName(inputStream.getInputStreamName());
      addressBean.setVirtualSensorName(vsensorConfig.getName());
      wrapper = findWrapper(addressBean);
      try {
        if (wrapper!=null && prepareStreamSource( streamSource,wrapper.getOutputFormat(),wrapper)) 
          break;
        else
          //TODO: remove wrapper from activeWrappers and release its resources
          wrapper=null;
      } catch (SQLException e) {
        logger.error(e.getMessage(),e);
        logger.error("Preparation of the stream source failed : "+streamSource.getAlias()+ " from the input stream : "+inputStream.getInputStreamName());
      }
    }
    return (wrapper!=null);
  }
  
  public boolean prepareStreamSource ( StreamSource streamSource ,DataField[] outputformat, AbstractWrapper wrapper ) throws InstantiationException, IllegalAccessException, SQLException {
    if (outputformat==null) {
      logger.error("Preparing the stream source failed because the wrapper : "+wrapper.getWrapperName()+" returns null for the >getOutputStructure< method!");
      return false;
    }
    streamSource.setWrapper ( wrapper );
    streamSource.getInputStream().addToRenamingMapping(streamSource.getAlias(), streamSource.getUIDStr());
    return true;
  }
  
  public void stopLoading ( ) {
    this.isActive = false;
    this.interrupt ( );
    for ( String configFile : Mappings.getAllKnownFileName ( ) ) {
      VirtualSensorPool sensorInstance = Mappings.getVSensorInstanceByFileName ( configFile );
      removeAllVSResources ( sensorInstance );
      logger.warn ( "Removing the resources associated with : " + sensorInstance.getConfig ( ).getFileName ( ) + " [done]." );
    }
    try {
      sm.shutdown ( );
    } catch ( SQLException e ) {
      e.printStackTrace ( );
    }finally {
      System.exit(0);
    }
  }
}
