package pt.itsector.notification.services.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pt.itsector.notification.avro.AvroNotification;

@Slf4j
@Service
public class DefaultKafkaProducer implements KafkaProducer {

    @Value("${spring.kafka.producer.topic-name}")
    String topic;

    @Autowired
    private KafkaTemplate<String, AvroNotification> kafkaTemplate;

    @Override
    public void sendMessage(AvroNotification avroNotification) {
        log.info("Sending {} to topic {}", avroNotification.toString(), topic);
        kafkaTemplate.send(topic, avroNotification);
    }
}