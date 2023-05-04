package pt.itsector.notification.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;
import pt.itsector.notification.mappers.BaseMapper;
import pt.itsector.notification.mappers.NotificationMapper;
import pt.itsector.notification.mappers.NotificationResultMapper;
import pt.itsector.notification.repository.NotificationRepository;
import pt.itsector.notification.repository.NotificationResultRepository;

@Slf4j
@Service
public class DefaultNotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationResultRepository notificationResultRepository;

    @KafkaListener(topics = "${spring.kafka.consumer.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveNotification(AvroNotification avroNotification) {
        log.info("Saving notification {} to database", avroNotification.toString());
        String notificationId = BaseMapper.getString(avroNotification.getNotificationId().toString());
        try {
            notificationRepository.save(NotificationMapper.mapToEntity(avroNotification));
        } catch (Exception ex) {
            log.error("Error saving notification {} to database", notificationId);
        }
    }

    @KafkaListener(topics = "${spring.kafka.consumer.result-topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveNotificationResult(AvroNotificationResult avroNotificationResult) {
        log.info("Saving notification result {} to database", avroNotificationResult.toString());
        String notificationId = BaseMapper.getString(avroNotificationResult.getNotificationId());
        try {
            notificationResultRepository.save(NotificationResultMapper.mapToEntity(avroNotificationResult));
        } catch (Exception ex) {
            log.error("Error saving notification result {} to database: {}", notificationId, ex.getMessage());
        }
    }
}
