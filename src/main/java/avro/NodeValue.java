/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class NodeValue extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1432030910815619894L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NodeValue\",\"namespace\":\"avro\",\"fields\":[{\"name\":\"node_id\",\"type\":\"long\"},{\"name\":\"value\",\"type\":\"int\"},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NodeValue> ENCODER =
      new BinaryMessageEncoder<NodeValue>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NodeValue> DECODER =
      new BinaryMessageDecoder<NodeValue>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<NodeValue> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<NodeValue> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<NodeValue> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NodeValue>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this NodeValue to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a NodeValue from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a NodeValue instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static NodeValue fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public long node_id;
  @Deprecated public int value;
  @Deprecated public long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NodeValue() {}

  /**
   * All-args constructor.
   * @param node_id The new value for node_id
   * @param value The new value for value
   * @param timestamp The new value for timestamp
   */
  public NodeValue(java.lang.Long node_id, java.lang.Integer value, java.lang.Long timestamp) {
    this.node_id = node_id;
    this.value = value;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return node_id;
    case 1: return value;
    case 2: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: node_id = (java.lang.Long)value$; break;
    case 1: value = (java.lang.Integer)value$; break;
    case 2: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'node_id' field.
   * @return The value of the 'node_id' field.
   */
  public long getNodeId() {
    return node_id;
  }


  /**
   * Sets the value of the 'node_id' field.
   * @param value the value to set.
   */
  public void setNodeId(long value) {
    this.node_id = value;
  }

  /**
   * Gets the value of the 'value' field.
   * @return The value of the 'value' field.
   */
  public int getValue() {
    return value;
  }


  /**
   * Sets the value of the 'value' field.
   * @param value the value to set.
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new NodeValue RecordBuilder.
   * @return A new NodeValue RecordBuilder
   */
  public static avro.NodeValue.Builder newBuilder() {
    return new avro.NodeValue.Builder();
  }

  /**
   * Creates a new NodeValue RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NodeValue RecordBuilder
   */
  public static avro.NodeValue.Builder newBuilder(avro.NodeValue.Builder other) {
    if (other == null) {
      return new avro.NodeValue.Builder();
    } else {
      return new avro.NodeValue.Builder(other);
    }
  }

  /**
   * Creates a new NodeValue RecordBuilder by copying an existing NodeValue instance.
   * @param other The existing instance to copy.
   * @return A new NodeValue RecordBuilder
   */
  public static avro.NodeValue.Builder newBuilder(avro.NodeValue other) {
    if (other == null) {
      return new avro.NodeValue.Builder();
    } else {
      return new avro.NodeValue.Builder(other);
    }
  }

  /**
   * RecordBuilder for NodeValue instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NodeValue>
    implements org.apache.avro.data.RecordBuilder<NodeValue> {

    private long node_id;
    private int value;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avro.NodeValue.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.node_id)) {
        this.node_id = data().deepCopy(fields()[0].schema(), other.node_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.value)) {
        this.value = data().deepCopy(fields()[1].schema(), other.value);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing NodeValue instance
     * @param other The existing instance to copy.
     */
    private Builder(avro.NodeValue other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.node_id)) {
        this.node_id = data().deepCopy(fields()[0].schema(), other.node_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.value)) {
        this.value = data().deepCopy(fields()[1].schema(), other.value);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'node_id' field.
      * @return The value.
      */
    public long getNodeId() {
      return node_id;
    }


    /**
      * Sets the value of the 'node_id' field.
      * @param value The value of 'node_id'.
      * @return This builder.
      */
    public avro.NodeValue.Builder setNodeId(long value) {
      validate(fields()[0], value);
      this.node_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'node_id' field has been set.
      * @return True if the 'node_id' field has been set, false otherwise.
      */
    public boolean hasNodeId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'node_id' field.
      * @return This builder.
      */
    public avro.NodeValue.Builder clearNodeId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'value' field.
      * @return The value.
      */
    public int getValue() {
      return value;
    }


    /**
      * Sets the value of the 'value' field.
      * @param value The value of 'value'.
      * @return This builder.
      */
    public avro.NodeValue.Builder setValue(int value) {
      validate(fields()[1], value);
      this.value = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'value' field has been set.
      * @return True if the 'value' field has been set, false otherwise.
      */
    public boolean hasValue() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'value' field.
      * @return This builder.
      */
    public avro.NodeValue.Builder clearValue() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public avro.NodeValue.Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public avro.NodeValue.Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NodeValue build() {
      try {
        NodeValue record = new NodeValue();
        record.node_id = fieldSetFlags()[0] ? this.node_id : (java.lang.Long) defaultValue(fields()[0]);
        record.value = fieldSetFlags()[1] ? this.value : (java.lang.Integer) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NodeValue>
    WRITER$ = (org.apache.avro.io.DatumWriter<NodeValue>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NodeValue>
    READER$ = (org.apache.avro.io.DatumReader<NodeValue>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.node_id);

    out.writeInt(this.value);

    out.writeLong(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.node_id = in.readLong();

      this.value = in.readInt();

      this.timestamp = in.readLong();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.node_id = in.readLong();
          break;

        case 1:
          this.value = in.readInt();
          break;

        case 2:
          this.timestamp = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









