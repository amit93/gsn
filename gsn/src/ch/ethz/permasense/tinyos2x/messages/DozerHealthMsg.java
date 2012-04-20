/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'DozerHealthMsg'
 * message type.
 */

package ch.ethz.permasense.tinyos2x.messages;

public class DozerHealthMsg extends ch.ethz.permasense.tinyos2x.messages.DataHeaderMsg {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 30;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 128;

    /** Create a new DozerHealthMsg of size 30. */
    public DozerHealthMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new DozerHealthMsg of the given data_length. */
    public DozerHealthMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg with the given data_length
     * and base offset.
     */
    public DozerHealthMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg using the given byte array
     * as backing store.
     */
    public DozerHealthMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public DozerHealthMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public DozerHealthMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg embedded in the given message
     * at the given base offset.
     */
    public DozerHealthMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerHealthMsg embedded in the given message
     * at the given base offset and length.
     */
    public DozerHealthMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <DozerHealthMsg> \n";
      try {
        s += "  [header.seqNr=0x"+Long.toHexString(get_header_seqNr())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.originatorID=0x"+Long.toHexString(get_header_originatorID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.aTime.low=0x"+Long.toHexString(get_header_aTime_low())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.aTime.high=0x"+Long.toHexString(get_header_aTime_high())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sample.valid=0x"+Long.toHexString(get_payload_sample_valid())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sample.no=0x"+Long.toHexString(get_payload_sample_no())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.uptime.low=0x"+Long.toHexString(get_payload_uptime_low())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.uptime.high=0x"+Long.toHexString(get_payload_uptime_high())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sysvoltage=0x"+Long.toHexString(get_payload_sysvoltage())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sdivoltage=0x"+Long.toHexString(get_payload_sdivoltage())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.temperature=0x"+Long.toHexString(get_payload_temperature())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.humidity=0x"+Long.toHexString(get_payload_humidity())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sibcurrent=0x"+Long.toHexString(get_payload_sibcurrent())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.msptemperature=0x"+Long.toHexString(get_payload_msptemperature())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.flashStatus=0x"+Long.toHexString(get_payload_flashStatus())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.queueSize=0x"+Long.toHexString(get_payload_queueSize())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.parentId=0x"+Long.toHexString(get_payload_parentId())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.hopCount=0x"+Long.toHexString(get_payload_hopCount())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.childCount=0x"+Long.toHexString(get_payload_childCount())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.seqNr
    //   Field type: int
    //   Offset (bits): 0
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.seqNr' is signed (false).
     */
    public static boolean isSigned_header_seqNr() {
        return false;
    }

    /**
     * Return whether the field 'header.seqNr' is an array (false).
     */
    public static boolean isArray_header_seqNr() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.seqNr'
     */
    public static int offset_header_seqNr() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.seqNr'
     */
    public static int offsetBits_header_seqNr() {
        return 0;
    }

    /**
     * Return the value (as a int) of the field 'header.seqNr'
     */
    public int get_header_seqNr() {
        return (int)getUIntBEElement(offsetBits_header_seqNr(), 16);
    }

    /**
     * Set the value of the field 'header.seqNr'
     */
    public void set_header_seqNr(int value) {
        setUIntBEElement(offsetBits_header_seqNr(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.seqNr'
     */
    public static int size_header_seqNr() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.seqNr'
     */
    public static int sizeBits_header_seqNr() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.originatorID
    //   Field type: int
    //   Offset (bits): 16
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.originatorID' is signed (false).
     */
    public static boolean isSigned_header_originatorID() {
        return false;
    }

    /**
     * Return whether the field 'header.originatorID' is an array (false).
     */
    public static boolean isArray_header_originatorID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.originatorID'
     */
    public static int offset_header_originatorID() {
        return (16 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.originatorID'
     */
    public static int offsetBits_header_originatorID() {
        return 16;
    }

    /**
     * Return the value (as a int) of the field 'header.originatorID'
     */
    public int get_header_originatorID() {
        return (int)getUIntBEElement(offsetBits_header_originatorID(), 16);
    }

    /**
     * Set the value of the field 'header.originatorID'
     */
    public void set_header_originatorID(int value) {
        setUIntBEElement(offsetBits_header_originatorID(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.originatorID'
     */
    public static int size_header_originatorID() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.originatorID'
     */
    public static int sizeBits_header_originatorID() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.aTime.low
    //   Field type: int
    //   Offset (bits): 32
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.aTime.low' is signed (false).
     */
    public static boolean isSigned_header_aTime_low() {
        return false;
    }

    /**
     * Return whether the field 'header.aTime.low' is an array (false).
     */
    public static boolean isArray_header_aTime_low() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.aTime.low'
     */
    public static int offset_header_aTime_low() {
        return (32 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.aTime.low'
     */
    public static int offsetBits_header_aTime_low() {
        return 32;
    }

    /**
     * Return the value (as a int) of the field 'header.aTime.low'
     */
    public int get_header_aTime_low() {
        return (int)getUIntBEElement(offsetBits_header_aTime_low(), 16);
    }

    /**
     * Set the value of the field 'header.aTime.low'
     */
    public void set_header_aTime_low(int value) {
        setUIntBEElement(offsetBits_header_aTime_low(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.aTime.low'
     */
    public static int size_header_aTime_low() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.aTime.low'
     */
    public static int sizeBits_header_aTime_low() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.aTime.high
    //   Field type: short
    //   Offset (bits): 48
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.aTime.high' is signed (false).
     */
    public static boolean isSigned_header_aTime_high() {
        return false;
    }

    /**
     * Return whether the field 'header.aTime.high' is an array (false).
     */
    public static boolean isArray_header_aTime_high() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.aTime.high'
     */
    public static int offset_header_aTime_high() {
        return (48 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.aTime.high'
     */
    public static int offsetBits_header_aTime_high() {
        return 48;
    }

    /**
     * Return the value (as a short) of the field 'header.aTime.high'
     */
    public short get_header_aTime_high() {
        return (short)getUIntBEElement(offsetBits_header_aTime_high(), 8);
    }

    /**
     * Set the value of the field 'header.aTime.high'
     */
    public void set_header_aTime_high(short value) {
        setUIntBEElement(offsetBits_header_aTime_high(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.aTime.high'
     */
    public static int size_header_aTime_high() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.aTime.high'
     */
    public static int sizeBits_header_aTime_high() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sample.valid
    //   Field type: byte
    //   Offset (bits): 56
    //   Size (bits): 1
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sample.valid' is signed (false).
     */
    public static boolean isSigned_payload_sample_valid() {
        return false;
    }

    /**
     * Return whether the field 'payload.sample.valid' is an array (false).
     */
    public static boolean isArray_payload_sample_valid() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sample.valid'
     */
    public static int offset_payload_sample_valid() {
        return (56 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sample.valid'
     */
    public static int offsetBits_payload_sample_valid() {
        return 56;
    }

    /**
     * Return the value (as a byte) of the field 'payload.sample.valid'
     */
    public byte get_payload_sample_valid() {
        return (byte)getUIntBEElement(offsetBits_payload_sample_valid(), 1);
    }

    /**
     * Set the value of the field 'payload.sample.valid'
     */
    public void set_payload_sample_valid(byte value) {
        setUIntBEElement(offsetBits_payload_sample_valid(), 1, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sample.valid'
     * WARNING: This field is not an even-sized number of bytes (1 bits).
     */
    public static int size_payload_sample_valid() {
        return (1 / 8) + 1;
    }

    /**
     * Return the size, in bits, of the field 'payload.sample.valid'
     */
    public static int sizeBits_payload_sample_valid() {
        return 1;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sample.no
    //   Field type: short
    //   Offset (bits): 57
    //   Size (bits): 15
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sample.no' is signed (false).
     */
    public static boolean isSigned_payload_sample_no() {
        return false;
    }

    /**
     * Return whether the field 'payload.sample.no' is an array (false).
     */
    public static boolean isArray_payload_sample_no() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sample.no'
     * WARNING: This field is not byte-aligned (bit offset 57).
     */
    public static int offset_payload_sample_no() {
        return (57 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sample.no'
     */
    public static int offsetBits_payload_sample_no() {
        return 57;
    }

    /**
     * Return the value (as a short) of the field 'payload.sample.no'
     */
    public short get_payload_sample_no() {
        return (short)getUIntBEElement(offsetBits_payload_sample_no(), 15);
    }

    /**
     * Set the value of the field 'payload.sample.no'
     */
    public void set_payload_sample_no(short value) {
        setUIntBEElement(offsetBits_payload_sample_no(), 15, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sample.no'
     * WARNING: This field is not an even-sized number of bytes (15 bits).
     */
    public static int size_payload_sample_no() {
        return (15 / 8) + 1;
    }

    /**
     * Return the size, in bits, of the field 'payload.sample.no'
     */
    public static int sizeBits_payload_sample_no() {
        return 15;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.uptime.low
    //   Field type: int
    //   Offset (bits): 72
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.uptime.low' is signed (false).
     */
    public static boolean isSigned_payload_uptime_low() {
        return false;
    }

    /**
     * Return whether the field 'payload.uptime.low' is an array (false).
     */
    public static boolean isArray_payload_uptime_low() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.uptime.low'
     */
    public static int offset_payload_uptime_low() {
        return (72 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.uptime.low'
     */
    public static int offsetBits_payload_uptime_low() {
        return 72;
    }

    /**
     * Return the value (as a int) of the field 'payload.uptime.low'
     */
    public int get_payload_uptime_low() {
        return (int)getUIntBEElement(offsetBits_payload_uptime_low(), 16);
    }

    /**
     * Set the value of the field 'payload.uptime.low'
     */
    public void set_payload_uptime_low(int value) {
        setUIntBEElement(offsetBits_payload_uptime_low(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.uptime.low'
     */
    public static int size_payload_uptime_low() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.uptime.low'
     */
    public static int sizeBits_payload_uptime_low() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.uptime.high
    //   Field type: short
    //   Offset (bits): 88
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.uptime.high' is signed (false).
     */
    public static boolean isSigned_payload_uptime_high() {
        return false;
    }

    /**
     * Return whether the field 'payload.uptime.high' is an array (false).
     */
    public static boolean isArray_payload_uptime_high() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.uptime.high'
     */
    public static int offset_payload_uptime_high() {
        return (88 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.uptime.high'
     */
    public static int offsetBits_payload_uptime_high() {
        return 88;
    }

    /**
     * Return the value (as a short) of the field 'payload.uptime.high'
     */
    public short get_payload_uptime_high() {
        return (short)getUIntBEElement(offsetBits_payload_uptime_high(), 8);
    }

    /**
     * Set the value of the field 'payload.uptime.high'
     */
    public void set_payload_uptime_high(short value) {
        setUIntBEElement(offsetBits_payload_uptime_high(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.uptime.high'
     */
    public static int size_payload_uptime_high() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.uptime.high'
     */
    public static int sizeBits_payload_uptime_high() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sysvoltage
    //   Field type: int
    //   Offset (bits): 96
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sysvoltage' is signed (false).
     */
    public static boolean isSigned_payload_sysvoltage() {
        return false;
    }

    /**
     * Return whether the field 'payload.sysvoltage' is an array (false).
     */
    public static boolean isArray_payload_sysvoltage() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sysvoltage'
     */
    public static int offset_payload_sysvoltage() {
        return (96 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sysvoltage'
     */
    public static int offsetBits_payload_sysvoltage() {
        return 96;
    }

    /**
     * Return the value (as a int) of the field 'payload.sysvoltage'
     */
    public int get_payload_sysvoltage() {
        return (int)getUIntBEElement(offsetBits_payload_sysvoltage(), 16);
    }

    /**
     * Set the value of the field 'payload.sysvoltage'
     */
    public void set_payload_sysvoltage(int value) {
        setUIntBEElement(offsetBits_payload_sysvoltage(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sysvoltage'
     */
    public static int size_payload_sysvoltage() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.sysvoltage'
     */
    public static int sizeBits_payload_sysvoltage() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sdivoltage
    //   Field type: int
    //   Offset (bits): 112
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sdivoltage' is signed (false).
     */
    public static boolean isSigned_payload_sdivoltage() {
        return false;
    }

    /**
     * Return whether the field 'payload.sdivoltage' is an array (false).
     */
    public static boolean isArray_payload_sdivoltage() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sdivoltage'
     */
    public static int offset_payload_sdivoltage() {
        return (112 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sdivoltage'
     */
    public static int offsetBits_payload_sdivoltage() {
        return 112;
    }

    /**
     * Return the value (as a int) of the field 'payload.sdivoltage'
     */
    public int get_payload_sdivoltage() {
        return (int)getUIntBEElement(offsetBits_payload_sdivoltage(), 16);
    }

    /**
     * Set the value of the field 'payload.sdivoltage'
     */
    public void set_payload_sdivoltage(int value) {
        setUIntBEElement(offsetBits_payload_sdivoltage(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sdivoltage'
     */
    public static int size_payload_sdivoltage() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.sdivoltage'
     */
    public static int sizeBits_payload_sdivoltage() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.temperature
    //   Field type: int
    //   Offset (bits): 128
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.temperature' is signed (false).
     */
    public static boolean isSigned_payload_temperature() {
        return false;
    }

    /**
     * Return whether the field 'payload.temperature' is an array (false).
     */
    public static boolean isArray_payload_temperature() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.temperature'
     */
    public static int offset_payload_temperature() {
        return (128 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.temperature'
     */
    public static int offsetBits_payload_temperature() {
        return 128;
    }

    /**
     * Return the value (as a int) of the field 'payload.temperature'
     */
    public int get_payload_temperature() {
        return (int)getUIntBEElement(offsetBits_payload_temperature(), 16);
    }

    /**
     * Set the value of the field 'payload.temperature'
     */
    public void set_payload_temperature(int value) {
        setUIntBEElement(offsetBits_payload_temperature(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.temperature'
     */
    public static int size_payload_temperature() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.temperature'
     */
    public static int sizeBits_payload_temperature() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.humidity
    //   Field type: int
    //   Offset (bits): 144
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.humidity' is signed (false).
     */
    public static boolean isSigned_payload_humidity() {
        return false;
    }

    /**
     * Return whether the field 'payload.humidity' is an array (false).
     */
    public static boolean isArray_payload_humidity() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.humidity'
     */
    public static int offset_payload_humidity() {
        return (144 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.humidity'
     */
    public static int offsetBits_payload_humidity() {
        return 144;
    }

    /**
     * Return the value (as a int) of the field 'payload.humidity'
     */
    public int get_payload_humidity() {
        return (int)getUIntBEElement(offsetBits_payload_humidity(), 16);
    }

    /**
     * Set the value of the field 'payload.humidity'
     */
    public void set_payload_humidity(int value) {
        setUIntBEElement(offsetBits_payload_humidity(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.humidity'
     */
    public static int size_payload_humidity() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.humidity'
     */
    public static int sizeBits_payload_humidity() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sibcurrent
    //   Field type: int
    //   Offset (bits): 160
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sibcurrent' is signed (false).
     */
    public static boolean isSigned_payload_sibcurrent() {
        return false;
    }

    /**
     * Return whether the field 'payload.sibcurrent' is an array (false).
     */
    public static boolean isArray_payload_sibcurrent() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sibcurrent'
     */
    public static int offset_payload_sibcurrent() {
        return (160 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sibcurrent'
     */
    public static int offsetBits_payload_sibcurrent() {
        return 160;
    }

    /**
     * Return the value (as a int) of the field 'payload.sibcurrent'
     */
    public int get_payload_sibcurrent() {
        return (int)getUIntBEElement(offsetBits_payload_sibcurrent(), 16);
    }

    /**
     * Set the value of the field 'payload.sibcurrent'
     */
    public void set_payload_sibcurrent(int value) {
        setUIntBEElement(offsetBits_payload_sibcurrent(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sibcurrent'
     */
    public static int size_payload_sibcurrent() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.sibcurrent'
     */
    public static int sizeBits_payload_sibcurrent() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.msptemperature
    //   Field type: int
    //   Offset (bits): 176
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.msptemperature' is signed (false).
     */
    public static boolean isSigned_payload_msptemperature() {
        return false;
    }

    /**
     * Return whether the field 'payload.msptemperature' is an array (false).
     */
    public static boolean isArray_payload_msptemperature() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.msptemperature'
     */
    public static int offset_payload_msptemperature() {
        return (176 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.msptemperature'
     */
    public static int offsetBits_payload_msptemperature() {
        return 176;
    }

    /**
     * Return the value (as a int) of the field 'payload.msptemperature'
     */
    public int get_payload_msptemperature() {
        return (int)getUIntBEElement(offsetBits_payload_msptemperature(), 16);
    }

    /**
     * Set the value of the field 'payload.msptemperature'
     */
    public void set_payload_msptemperature(int value) {
        setUIntBEElement(offsetBits_payload_msptemperature(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.msptemperature'
     */
    public static int size_payload_msptemperature() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.msptemperature'
     */
    public static int sizeBits_payload_msptemperature() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.flashStatus
    //   Field type: int
    //   Offset (bits): 192
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.flashStatus' is signed (false).
     */
    public static boolean isSigned_payload_flashStatus() {
        return false;
    }

    /**
     * Return whether the field 'payload.flashStatus' is an array (false).
     */
    public static boolean isArray_payload_flashStatus() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.flashStatus'
     */
    public static int offset_payload_flashStatus() {
        return (192 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.flashStatus'
     */
    public static int offsetBits_payload_flashStatus() {
        return 192;
    }

    /**
     * Return the value (as a int) of the field 'payload.flashStatus'
     */
    public int get_payload_flashStatus() {
        return (int)getUIntBEElement(offsetBits_payload_flashStatus(), 16);
    }

    /**
     * Set the value of the field 'payload.flashStatus'
     */
    public void set_payload_flashStatus(int value) {
        setUIntBEElement(offsetBits_payload_flashStatus(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.flashStatus'
     */
    public static int size_payload_flashStatus() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.flashStatus'
     */
    public static int sizeBits_payload_flashStatus() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.queueSize
    //   Field type: short
    //   Offset (bits): 208
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.queueSize' is signed (false).
     */
    public static boolean isSigned_payload_queueSize() {
        return false;
    }

    /**
     * Return whether the field 'payload.queueSize' is an array (false).
     */
    public static boolean isArray_payload_queueSize() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.queueSize'
     */
    public static int offset_payload_queueSize() {
        return (208 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.queueSize'
     */
    public static int offsetBits_payload_queueSize() {
        return 208;
    }

    /**
     * Return the value (as a short) of the field 'payload.queueSize'
     */
    public short get_payload_queueSize() {
        return (short)getUIntBEElement(offsetBits_payload_queueSize(), 8);
    }

    /**
     * Set the value of the field 'payload.queueSize'
     */
    public void set_payload_queueSize(short value) {
        setUIntBEElement(offsetBits_payload_queueSize(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.queueSize'
     */
    public static int size_payload_queueSize() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.queueSize'
     */
    public static int sizeBits_payload_queueSize() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.parentId
    //   Field type: int
    //   Offset (bits): 216
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.parentId' is signed (false).
     */
    public static boolean isSigned_payload_parentId() {
        return false;
    }

    /**
     * Return whether the field 'payload.parentId' is an array (false).
     */
    public static boolean isArray_payload_parentId() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.parentId'
     */
    public static int offset_payload_parentId() {
        return (216 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.parentId'
     */
    public static int offsetBits_payload_parentId() {
        return 216;
    }

    /**
     * Return the value (as a int) of the field 'payload.parentId'
     */
    public int get_payload_parentId() {
        return (int)getUIntBEElement(offsetBits_payload_parentId(), 16);
    }

    /**
     * Set the value of the field 'payload.parentId'
     */
    public void set_payload_parentId(int value) {
        setUIntBEElement(offsetBits_payload_parentId(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.parentId'
     */
    public static int size_payload_parentId() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.parentId'
     */
    public static int sizeBits_payload_parentId() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.hopCount
    //   Field type: byte
    //   Offset (bits): 232
    //   Size (bits): 4
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.hopCount' is signed (false).
     */
    public static boolean isSigned_payload_hopCount() {
        return false;
    }

    /**
     * Return whether the field 'payload.hopCount' is an array (false).
     */
    public static boolean isArray_payload_hopCount() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.hopCount'
     */
    public static int offset_payload_hopCount() {
        return (232 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.hopCount'
     */
    public static int offsetBits_payload_hopCount() {
        return 232;
    }

    /**
     * Return the value (as a byte) of the field 'payload.hopCount'
     */
    public byte get_payload_hopCount() {
        return (byte)getUIntBEElement(offsetBits_payload_hopCount(), 4);
    }

    /**
     * Set the value of the field 'payload.hopCount'
     */
    public void set_payload_hopCount(byte value) {
        setUIntBEElement(offsetBits_payload_hopCount(), 4, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.hopCount'
     * WARNING: This field is not an even-sized number of bytes (4 bits).
     */
    public static int size_payload_hopCount() {
        return (4 / 8) + 1;
    }

    /**
     * Return the size, in bits, of the field 'payload.hopCount'
     */
    public static int sizeBits_payload_hopCount() {
        return 4;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.childCount
    //   Field type: byte
    //   Offset (bits): 236
    //   Size (bits): 4
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.childCount' is signed (false).
     */
    public static boolean isSigned_payload_childCount() {
        return false;
    }

    /**
     * Return whether the field 'payload.childCount' is an array (false).
     */
    public static boolean isArray_payload_childCount() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.childCount'
     * WARNING: This field is not byte-aligned (bit offset 236).
     */
    public static int offset_payload_childCount() {
        return (236 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.childCount'
     */
    public static int offsetBits_payload_childCount() {
        return 236;
    }

    /**
     * Return the value (as a byte) of the field 'payload.childCount'
     */
    public byte get_payload_childCount() {
        return (byte)getUIntBEElement(offsetBits_payload_childCount(), 4);
    }

    /**
     * Set the value of the field 'payload.childCount'
     */
    public void set_payload_childCount(byte value) {
        setUIntBEElement(offsetBits_payload_childCount(), 4, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.childCount'
     * WARNING: This field is not an even-sized number of bytes (4 bits).
     */
    public static int size_payload_childCount() {
        return (4 / 8) + 1;
    }

    /**
     * Return the size, in bits, of the field 'payload.childCount'
     */
    public static int sizeBits_payload_childCount() {
        return 4;
    }

}
