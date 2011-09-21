#! /usr/bin/python
# -*- coding: UTF-8 -*-
__author__      = "Tonio Gsell <tgsell@tik.ee.ethz.ch>"
__copyright__   = "Copyright 2010, ETH Zurich, Switzerland, Tonio Gsell"
__license__     = "GPL"
__version__     = "$Revision$"
__date__        = "$Date$"
__id__          = "$Id$"
__source__      = "$URL$"

import logging
import Queue
import os
import time
from threading import Thread, Event, Lock

from ScheduleHandler import SUBPROCESS_BUG_BYPASS
if SUBPROCESS_BUG_BYPASS:
    import SubprocessFake
    subprocess = SubprocessFake
else:
    import subprocess

import BackLogMessage
from AbstractPlugin import AbstractPluginClass
from SpecialAPI import PowerControl

try: 
    import serial
except ImportError, e: 
    print "Please install PySerial first."
    sys.exit(1)

DEFAULT_BACKLOG = True
GPHOTO2 = '/usr/bin/gphoto2'
PICTUREFOLDER = '/media/card/backlog/binaryplugin/camera1/'
POSTFIX='.%C'
DEFAULT_GPHOTO2_SETTINGS = ['/main/settings/capturetarget=1','/main/imgsettings/imagequality=3','/main/imgsettings/imagesize=0']

class CamZillaPluginClass(AbstractPluginClass, PowerControl):
    '''
    This plugin offers the functionality to control the CamZilla robot.
    '''

    '''
    data/instance attributes:
    _manualControl
    _delay
    _writeLock
    _xMaxRotation
    _xRotationToPulse
    _yMaxRotation
    _yRotationToPulse
    _taskqueue
    _plugStop
    _isBusy
    '''
    
    def __init__(self, parent, config):
        PowerControl.__init__(self, parent)
        AbstractPluginClass.__init__(self, parent, config, DEFAULT_BACKLOG)
        self._isBusy = True
        
        device = self.getOptionValue('device_name')
        if device is None:
            raise TypeError('no device_name specified')
        
        value = self.getOptionValue('max_horizontal_rotation')
        if value is None:
            raise TypeError('no max_horizontal_rotation value specified')
        else:
            self._xMaxRotation = int(value)
        
        value = self.getOptionValue('max_vertical_rotation')
        if value is None:
            raise TypeError('no max_vertical_rotation value specified')
        else:
            self._yMaxRotation = int(value)
            
        self.info('maximum possible robot rotation in degrees: x=%d, y=%d' % (self._xMaxRotation,self._yMaxRotation))
        self.info('connecting to device %s' % (device,))
        
        try:
            self._serial = serial.Serial(device)
        except serial.SerialException, e:
            raise TypeError('could not initialize serial source: %s' % (e,))
        self.info(self._serial.readline())
        
        if not os.path.isdir(PICTUREFOLDER):
            self.warning('picture folder >%s< is not a directory -> creating it' % (PICTUREFOLDER,))
            os.makedirs(PICTUREFOLDER)
            
        if not os.path.exists(GPHOTO2):
            raise TypeError('%s does not exist' % (GPHOTO2,))
        if not os.access(GPHOTO2, os.X_OK):
            raise TypeError('%s can not be executed' % (GPHOTO2,))
        
        if self.getUsb3Status():
            self.info('USB3 port is turned on')
        else:
            self.info('USB3 port is turned off')
        
        if self.getPhotoCamStatus():
            self.info('photo camera is turned on')
        else:
            self.info('photo camera is turned off')
        
        self._taskqueue = Queue.Queue()
        self._delay = Event()
        self._writeLock = Lock()
        self._manualControl = True
        self._plugStop = False
    
    
    def getMsgType(self):
        return BackLogMessage.CAMZILLA_MESSAGE_TYPE
        
        
    def isBusy(self):
        return self._isBusy
        
        
    def needsWLAN(self):
        return False
    
    
    def msgReceived(self, data):
        self.action(data)
       
        
    def run(self):
        self.name = 'CamZillaPlugin-Thread'
        self.info('started')
        
        self._initRobot()

        while not self._plugStop:
            if self._taskqueue.empty():
                self._isBusy = False
            task = self._taskqueue.get()
            self._isBusy = True
            if self._plugStop:
                try:
                    self._taskqueue.task_done()
                except ValueError, e:
                    self.exception(e)
                break
            
            try:
                parsedTask = self._parseTask(task)
                
                # turn the USB3 port on
                self.usb3On()
                # turn the photo camera on
                self.photoCamOn()
                
                now = time.time()
                self.info('executing command: start(%s,%s) pictures(%s,%s) rotation(%s,%s) delay(%s) gphoto2(%s)' % (str(parsedTask[0]), str(parsedTask[1]), str(parsedTask[2]), str(parsedTask[3]), str(parsedTask[4]), str(parsedTask[5]), str(parsedTask[6]), str(parsedTask[7])))
                pic = 1
                for y in range(parsedTask[1], parsedTask[1]+(parsedTask[3]*parsedTask[5]), parsedTask[5]):
                    self._write('y=%d' % (int(round(y*self._yRotationToPulse)),))
                    for x in range(parsedTask[0], parsedTask[0]+(parsedTask[2]*parsedTask[4]), parsedTask[4]):
                        self._write('x=%d' % (int(round(x*self._xRotationToPulse)),))
                        if self._plugStop:
                            break
                        if parsedTask[6] > 0:
                            self._delay.wait(parsedTask[6])
                        try:
                            self.info('taking picture number %d/%d at position (%d,%d)' % (pic,parsedTask[2]*parsedTask[3],x,y))
                            self._takePicture(parsedTask[7])
                            pic += 1
                        except Exception, e:
                            self.exception(str(e))
                    if self._plugStop:
                        break
                
                self.processMsg(self.getTimeStamp(), [int(now*1000)] + parsedTask)
                         
                
                if not self._plugStop:
                    self._downloadPictures(time.strftime('%Y%m%d_%H%M%S', time.gmtime(now)))
                
                # turn the photo camera off
                self.photoCamOff()
                # turn the USB3 port off
                self.usb3Off()
                
            except Exception, e:
                self.exception(str(e))
                
            try:
                self._taskqueue.task_done()
            except ValueError, e:
                self.exception(e)
        
        self.info('died')


    def action(self, parameters):
        self._taskqueue.put(parameters)
    
    
    def stop(self):
        self._write('j=on')
        self._isBusy = False
        self._plugStop = True
        self._taskqueue.put('end')
        
        
    def _parseTask(self, task):
        params = task.split(' ')
        ret = [None]*8
        for param in params:
            param = param.lower()
            if param.startswith('start'):
                startX, startY = param[6:-1].split(',')
                ret[0] = int(startX)
                ret[1] = int(startY)
            elif param.startswith('pictures'):
                picsX, picsY = param[9:-1].split(',')
                ret[2] = int(picsX)
                ret[3] = int(picsY)
            elif param.startswith('rotation'):
                rotationX, rotationY = param[9:-1].split(',')
                ret[4] = int(rotationX)
                ret[5] = int(rotationY)
            elif param.startswith('delay'):
                ret[6] = int(param[6:-1])
            elif param.startswith('gphoto2'):
                ret[7] = param[8:-1].split(',')
            else:
                self.error('unrecognized parameter >%s< in task >%s<' % (param,task))
        if not ret[0]:
            ret[0] = 0
        if not ret[1]:
            ret[1] = 0
        if not ret[2]:
            ret[2] = 1
        if not ret[3]:
            ret[3] = 1
        if not ret[4] and ret[2] > 1:
            raise TypeError('x-rotation has to be specified if more than one picture has to be taken in x-direction')
        if not ret[5] and ret[3] > 1:
            raise TypeError('y-rotation has to be specified if more than one picture has to be taken in y-direction')
        if not ret[4]:
            ret[4] = 1
        if not ret[5]:
            ret[5] = 1
        if not ret[6]:
            ret[6] = 0
        if not ret[7]:
            ret[7] = []
        return ret
        
        
        
    def _takePicture(self, settings):
        configlist = []
        
        for default in DEFAULT_GPHOTO2_SETTINGS:
            notavailable = True
            def_begin = default.split('=')[0].strip()
            for setting in settings:
                if def_begin == setting.split('=')[0].strip():
                    notavailable = False
                    break
            if notavailable:
                settings.append(default)
        
        sets = []     
        for setting in settings:
            sets.append('--set-config %s' % (setting.strip(),))
        
        command = [GPHOTO2, '--port="usb:"', '--force-overwrite', '--quiet'] + sets + ['--capture-image']
        self.debug('taking picture >%s<' % (str(command),))
        self._execGphoto2(command)
        
        
    def _downloadPictures(self, datestring):
        self._execGphoto2([GPHOTO2, '--port="usb:"', '--quiet', '--get-all-files', '--filename=' + datestring + '-%03n.%C', '--recurse', '--delete-all-files'], PICTUREFOLDER)
        
        
    def _execGphoto2(self, params, cwd=None):
        p = subprocess.Popen(params, stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=cwd)
        ret = p.wait()
        output = p.communicate()
        if output[0]:
            self.info(output[0])
        elif not output[1]:
            self.error('%s has not generated any output' % (GPHOTO2,))
        if output[1]:
            self.error(output[1])
        return ret
        
    
    def _initRobot(self):
        self._write("j=off")
        cal = self._write("cal")
        self._xRotationToPulse = cal[0] / (self._xMaxRotation / 2.0)
        self._yRotationToPulse = cal[1] / (self._yMaxRotation / 2.0)
        
        
        
    def _write(self, com):
        if not self._plugStop:
            self._writeLock.acquire()
            self.debug('servo control write: %s' % (com,))
            if com == 'j=on' or com == 'j=off':
                self._serial.write(com + "\n")
                ans = self._serial.readline().strip()
                self.debug('servo control answer: j=..: %s' % (ans,))
                if com != ans:
                    self._writeLock.release()
                    raise Exception('return value (%s) does not match command (%s)' % (ans, com))
                self._manualControl = (com == 'j=on')
            elif self._manualControl:
                self._writeLock.release()
                raise Exception('manual joystick control is turned on -> command (%s) will not be executed' % (com,))
            elif com == 'cal':
                self._serial.write(com + "\n")
                cal1 = self._serial.readline().strip()
                if cal1 == 'j=on':
                    self._manualControl = True
                    self._writeLock.release()
                    raise Exception('manual joystick control has been turned on -> no more commands will be sent to CamZilla until joystick control has been turned off')
                elif cal1 == '!cal':
                    self._writeLock.release()
                    raise Exception('could not calibrate')
                self.debug('servo control answer: cal(1): %s' % (cal1))
                cal2 = self._serial.readline().strip()
                self.debug('servo control answer: cal(2): %s' % (cal2))
                cal1 = cal1[5:-1].split(',')
                cal2 = cal2.split('=')[1].split('/')
                self._writeLock.release()
                return (int(cal1[0]), int(cal1[1]), int(cal2[0]), int(cal2[1]))
            elif com.startswith('x=') or com.startswith('y='):
                self._serial.write(com + "\n")
                ans = self._serial.readline().strip()
                self.debug('servo control answer: x=..: %s' % (ans,))
                if ans == '!cal':
                    self._writeLock.release()
                    raise Exception('not yet calibrated')
                elif ans.startswith('x/y='):
                    spl = ans.split('=')[1].split('/')
                    xLimit = yLimit = False
                    if (spl[0].endswith('L')):
                        spl[0] = spl[0][:-1]
                        xLimit = True
                    if (spl[1].endswith('L')):
                        spl[1] = spl[1][:-1]
                        yLimit = True
                    self._writeLock.release()
                    return (int(spl[0]), int(spl[1]), xLimit, yLimit)
                elif ans == 'j=on':
                    self._manualControl = True
                    self._writeLock.release()
                    raise Exception('manual joystick control has been turned on -> no more commands will be sent to CamZilla until joystick control has been turned off')
                else:
                    self._writeLock.release()
                    raise Exception('unknown return value for command (%s): %s' % (com, ans))
            else:
                self._writeLock.release()
                raise TypeError('command (%s) unknown' % (com,))
            self._writeLock.release()
        else:
            self.warning('plugin has been stopped -> command will not be executed')
    


if __name__ == '__main__':
    import signal
    import ConfigParser
    import optparse
    import logging.config

    class GSNPeerDummy():
        def processMsg(self, msgType, timestamp, payload, priority, backlog=False):
            pass
    class MainDummy():
        def __init__(self):
            self.gsnpeer = GSNPeerDummy()
            self.duty_cycle_mode = False
        def incrementExceptionCounter(self):
            pass
        def incrementErrorCounter(self):
            pass
        def runPluginRemoteAction(self):
            pass
    
    parser = optparse.OptionParser('usage: %prog [options]')
    
    parser.add_option('-c', '--config', type='string', dest='config_file', default='/etc/backlog.cfg',
                      help='Configuration file. Default: /etc/backlog.cfg', metavar='FILE')
    parser.add_option('-x', '--startx', type='int', dest='startX', default=0,
                      help='Lower-left horizontal starting point', metavar='INT')
    parser.add_option('-y', '--starty', type='int', dest='startY', default=0,
                      help='Lower-left vertical starting point', metavar='INT')
    parser.add_option('--picsx', type='int', dest='picturesX', default=1,
                      help='Number of pictures taken horizontally', metavar='INT')
    parser.add_option('--picsy', type='int', dest='picturesY', default=1,
                      help='Number of pictures taken vertically', metavar='INT')
    parser.add_option('--rotx', type='int', dest='rotationX', default=1,
                      help='Horizontal rotation in degrees between pictures', metavar='INT')
    parser.add_option('--roty', type='int', dest='rotationY', default=1,
                      help='Vertical rotation in degrees between pictures', metavar='INT')
    parser.add_option('-d', '--delay', type='int', dest='delay', default=0,
                      help='Delay between rotation and picture taking', metavar='INT')
    parser.add_option('-g', '--gphoto2', type='string', dest='gphoto2', default='/main/settings/capturetarget=1,/main/imgsettings/imagequality=3,/main/imgsettings/imagesize=0',
                      help='Comma separated configurations for gphoto2', metavar='CONFIGS')
    
    (opt, args) = parser.parse_args()

    # read config file for logging options
    try:
        logging.config.fileConfig(opt.config_file)
        logging.logProcesses = 0

        # read config file for other options
        config = ConfigParser.SafeConfigParser()
        config.optionxform = str # case sensitive
        config.read(opt.config_file)
    except ConfigParser.NoSectionError, e:
        print e.__str__()
    
    try:
        camZilla = CamZillaPluginClass(MainDummy(), dict(config.items('CamZillaPlugin_options')))
        camZilla.start()
        camZilla.action('start(%d,%d) pictures(%d,%d) rotation(%d,%d) delay(%d) gphoto2(%s)' % (opt.startX, opt.startY, opt.picturesX, opt.picturesY, opt.rotationX, opt.rotationY, opt.delay, opt.gphoto2))
        signal.pause()
    except KeyboardInterrupt:
        print 'KeyboardInterrupt'
        camZilla.stop()
        