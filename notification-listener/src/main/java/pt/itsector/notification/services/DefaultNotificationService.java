package pt.itsector.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.mappers.NotificationMapper;
import pt.itsector.notification.models.NotificationRequest;
import pt.itsector.notification.services.producer.KafkaProducer;

import java.util.UUID;

@Service
public class DefaultNotificationService implements NotificationService {

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public String sendNotification(NotificationRequest request) {
        AvroNotification avroNotification = NotificationMapper.mapToAvro(UUID.randomUUID().toString(), request);

        kafkaProducer.sendMessage(avroNotification);

        return avroNotification.getNotificationId().toString();
    }
}
