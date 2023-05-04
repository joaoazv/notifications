package pt.itsector.notification.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TransactionAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionTimedOutException;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;
import pt.itsector.notification.mappers.NotificationResultMapper;
import pt.itsector.notification.models.Status;
import pt.itsector.notification.services.producer.KafkaProducer;

@Slf4j
@Service
public class DefaultNotificationService {

    int counter = 0;

    @Autowired
    KafkaProducer kafkaProducer;

    @KafkaListener(topics = "${spring.kafka.consumer.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveNotification(AvroNotification avroNotification) {
        log.info("RECEIVED NOTIFICATION {}", avroNotification.toString());
        String notificationId = avroNotification.getNotificationId().toString();
        try {
            // IMPLEMENT SDK TO SEND NOTIFICATION BY TYPE
            mockSDK();

            sendResult(notificationId, Status.SENT);
        } catch (Exception ex) {
            log.error("Error sending notification {}", notificationId);
            sendResult(notificationId, Status.FAILED, ex.getMessage());
        }
    }

    private void mockSDK() throws Exception {
        counter++;
        if (counter % 8 == 0) {
            throw new TransactionTimedOutException("TIMEOUT");
        } else if (counter % 10 == 0) {
            throw new TransactionAbortedException("SERVER ERROR");
        } else {
            Thread.sleep(2000);
        }
    }

    public void sendResult(String notificationId, Status status) {
        sendResult(notificationId, status, null);
    }

    public void sendResult(String notificationId, Status status, String statusMessage) {
        AvroNotificationResult avroNotificationResult = NotificationResultMapper.mapToAvro(notificationId, status, statusMessage);

        kafkaProducer.sendMessage(avroNotificationResult);
    }
}
