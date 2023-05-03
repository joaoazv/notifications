package pt.itsector.notification.mappers;

import java.util.Arrays;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;

@Slf4j
@NoArgsConstructor
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {

    private Schema getSchemaByTopic(String topic) {
        switch (topic) {
            case "notification-service":
                return AvroNotification.getClassSchema();
            case "notification-result-service":
                return AvroNotificationResult.getClassSchema();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String topic, byte[] bytes) {
        T result = null;
        try {
            if (bytes != null) {
                log.debug("data='{}'", DatatypeConverter.printHexBinary(bytes));

                DatumReader<T> datumReader = new SpecificDatumReader<>(getSchemaByTopic(topic));
                Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);

                result = (T) datumReader.read(null, decoder);
                log.debug("deserialized data='{}'", result);
            }
        } catch (Exception ex) {
            throw new SerializationException(
                    "Can't deserialize data '" + Arrays.toString(bytes) + "' from topic '" + topic + "'", ex);
        }
        return result;
    }
}
