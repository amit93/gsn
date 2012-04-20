/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'DozerEnviroScanDataMsg'
 * message type.
 */

package ch.ethz.permasense.tinyos2x.messages;

public class DozerEnviroScanDataMsg extends ch.ethz.permasense.tinyos2x.messages.DataHeaderMsg {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 30;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 184;

    /** Create a new DozerEnviroScanDataMsg of size 30. */
    public DozerEnviroScanDataMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new DozerEnviroScanDataMsg of the given data_length. */
    public DozerEnviroScanDataMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg with the given data_length
     * and base offset.
     */
    public DozerEnviroScanDataMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg using the given byte array
     * as backing store.
     */
    public DozerEnviroScanDataMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public DozerEnviroScanDataMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public DozerEnviroScanDataMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg embedded in the given message
     * at the given base offset.
     */
    public DozerEnviroScanDataMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerEnviroScanDataMsg embedded in the given message
     * at the given base offset and length.
     */
    public DozerEnviroScanDataMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <DozerEnviroScanDataMsg> \n";
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
        s += "  [payload.measurement_type=0x"+Long.toHexString(get_payload_measurement_type())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.m1=0x"+Long.toHexString(get_payload_m1())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.m2=0x"+Long.toHexString(get_payload_m2())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.m3=0x"+Long.toHexString(get_payload_m3())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.m4=0x"+Long.toHexString(get_payload_m4())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.m5=0x"+Long.toHexString(get_payload_m5())+"]\n";
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
    // Accessor methods for field: payload.measurement_type
    //   Field type: short
    //   Offset (bits): 72
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.measurement_type' is signed (false).
     */
    public static boolean isSigned_payload_measurement_type() {
        return false;
    }

    /**
     * Return whether the field 'payload.measurement_type' is an array (false).
     */
    public static boolean isArray_payload_measurement_type() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.measurement_type'
     */
    public static int offset_payload_measurement_type() {
        return (72 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.measurement_type'
     */
    public static int offsetBits_payload_measurement_type() {
        return 72;
    }

    /**
     * Return the value (as a short) of the field 'payload.measurement_type'
     */
    public short get_payload_measurement_type() {
        return (short)getUIntBEElement(offsetBits_payload_measurement_type(), 8);
    }

    /**
     * Set the value of the field 'payload.measurement_type'
     */
    public void set_payload_measurement_type(short value) {
        setUIntBEElement(offsetBits_payload_measurement_type(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.measurement_type'
     */
    public static int size_payload_measurement_type() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.measurement_type'
     */
    public static int sizeBits_payload_measurement_type() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.m1
    //   Field type: int
    //   Offset (bits): 80
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.m1' is signed (false).
     */
    public static boolean isSigned_payload_m1() {
        return false;
    }

    /**
     * Return whether the field 'payload.m1' is an array (false).
     */
    public static boolean isArray_payload_m1() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.m1'
     */
    public static int offset_payload_m1() {
        return (80 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.m1'
     */
    public static int offsetBits_payload_m1() {
        return 80;
    }

    /**
     * Return the value (as a int) of the field 'payload.m1'
     */
    public int get_payload_m1() {
        return (int)getSIntBEElement(offsetBits_payload_m1(), 32);
    }

    /**
     * Set the value of the field 'payload.m1'
     */
    public void set_payload_m1(int value) {
        setSIntBEElement(offsetBits_payload_m1(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.m1'
     */
    public static int size_payload_m1() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.m1'
     */
    public static int sizeBits_payload_m1() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.m2
    //   Field type: int
    //   Offset (bits): 112
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.m2' is signed (false).
     */
    public static boolean isSigned_payload_m2() {
        return false;
    }

    /**
     * Return whether the field 'payload.m2' is an array (false).
     */
    public static boolean isArray_payload_m2() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.m2'
     */
    public static int offset_payload_m2() {
        return (112 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.m2'
     */
    public static int offsetBits_payload_m2() {
        return 112;
    }

    /**
     * Return the value (as a int) of the field 'payload.m2'
     */
    public int get_payload_m2() {
        return (int)getSIntBEElement(offsetBits_payload_m2(), 32);
    }

    /**
     * Set the value of the field 'payload.m2'
     */
    public void set_payload_m2(int value) {
        setSIntBEElement(offsetBits_payload_m2(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.m2'
     */
    public static int size_payload_m2() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.m2'
     */
    public static int sizeBits_payload_m2() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.m3
    //   Field type: int
    //   Offset (bits): 144
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.m3' is signed (false).
     */
    public static boolean isSigned_payload_m3() {
        return false;
    }

    /**
     * Return whether the field 'payload.m3' is an array (false).
     */
    public static boolean isArray_payload_m3() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.m3'
     */
    public static int offset_payload_m3() {
        return (144 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.m3'
     */
    public static int offsetBits_payload_m3() {
        return 144;
    }

    /**
     * Return the value (as a int) of the field 'payload.m3'
     */
    public int get_payload_m3() {
        return (int)getSIntBEElement(offsetBits_payload_m3(), 32);
    }

    /**
     * Set the value of the field 'payload.m3'
     */
    public void set_payload_m3(int value) {
        setSIntBEElement(offsetBits_payload_m3(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.m3'
     */
    public static int size_payload_m3() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.m3'
     */
    public static int sizeBits_payload_m3() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.m4
    //   Field type: int
    //   Offset (bits): 176
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.m4' is signed (false).
     */
    public static boolean isSigned_payload_m4() {
        return false;
    }

    /**
     * Return whether the field 'payload.m4' is an array (false).
     */
    public static boolean isArray_payload_m4() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.m4'
     */
    public static int offset_payload_m4() {
        return (176 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.m4'
     */
    public static int offsetBits_payload_m4() {
        return 176;
    }

    /**
     * Return the value (as a int) of the field 'payload.m4'
     */
    public int get_payload_m4() {
        return (int)getSIntBEElement(offsetBits_payload_m4(), 32);
    }

    /**
     * Set the value of the field 'payload.m4'
     */
    public void set_payload_m4(int value) {
        setSIntBEElement(offsetBits_payload_m4(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.m4'
     */
    public static int size_payload_m4() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.m4'
     */
    public static int sizeBits_payload_m4() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.m5
    //   Field type: int
    //   Offset (bits): 208
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.m5' is signed (false).
     */
    public static boolean isSigned_payload_m5() {
        return false;
    }

    /**
     * Return whether the field 'payload.m5' is an array (false).
     */
    public static boolean isArray_payload_m5() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.m5'
     */
    public static int offset_payload_m5() {
        return (208 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.m5'
     */
    public static int offsetBits_payload_m5() {
        return 208;
    }

    /**
     * Return the value (as a int) of the field 'payload.m5'
     */
    public int get_payload_m5() {
        return (int)getSIntBEElement(offsetBits_payload_m5(), 32);
    }

    /**
     * Set the value of the field 'payload.m5'
     */
    public void set_payload_m5(int value) {
        setSIntBEElement(offsetBits_payload_m5(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.m5'
     */
    public static int size_payload_m5() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.m5'
     */
    public static int sizeBits_payload_m5() {
        return 32;
    }

}
