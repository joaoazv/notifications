package pt.itsector.notification.services.producer;

import pt.itsector.notification.avro.AvroNotificationResult;

public interface KafkaProducer {
    void sendMessage(AvroNotificationResult avroNotificationResult);
}
