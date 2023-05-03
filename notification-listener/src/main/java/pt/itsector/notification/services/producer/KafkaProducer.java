package pt.itsector.notification.services.producer;

import pt.itsector.notification.avro.AvroNotification;

public interface KafkaProducer {
    void sendMessage(AvroNotification avroNotification);
}
