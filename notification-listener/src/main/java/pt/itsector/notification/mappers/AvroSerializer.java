package pt.itsector.notification.mappers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] result = null;
            if (data != null) {
                log.debug("data='{}'", data);
                BinaryEncoder binaryEncoder =
                        EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
                DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(data.getSchema());
                datumWriter.write(data, binaryEncoder);
                binaryEncoder.flush();
                result = byteArrayOutputStream.toByteArray();
                log.debug("serialized data='{}'", DatatypeConverter.printHexBinary(result));
            }
            return result;
        } catch (IOException ex) {
            throw new SerializationException(
                    "Can't serialize data='" + data + "' for topic='" + topic + "'", ex);
        }
    }
}