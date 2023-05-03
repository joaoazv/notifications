package pt.itsector.notification.services.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;

@Slf4j
@Service
public class DefaultKafkaProducer implements KafkaProducer {

    @Value("${spring.kafka.producer.topic-name}")
    String topic;

    @Autowired
    private KafkaTemplate<String, AvroNotificationResult> kafkaTemplate;

    @Override
    public void sendMessage(AvroNotificationResult avroNotificationResult) {
        log.info("Sending {} to topic {}", avroNotificationResult.toString(), topic);
        kafkaTemplate.send(topic, avroNotificationResult);
    }
}