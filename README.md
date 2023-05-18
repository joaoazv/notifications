# Notifications
The goal of this project is to create a system that sends notifications to users based on certain events. The system will consist of several components:

[![](https://mermaid.ink/img/pako:eNqFkU9LxDAQxb9KmIMobBb02IOn3sST3poi02Rqg21SJpMVWfa7Gy3-2bWscxrmPd784O3BRkdQQT_GVzsgi3qsTVBlUu6eGedB3WH_gsstRPG9tyg-Bj36JBSI9VbhjqPa6lslcfb2ummOjIl45y217RJy3qN0iTmSXPelqosTxacZxQ7EK3g_4h_Am5PnTCmPssr5n_Uc7hJDwa3Rfbu01r-VJ9c1lzUKdpjoqoUNTMQTeldK2n8EGZCBJjJQldVRj4XHgAmHYsUs8eEtWKiEM20gzw6Fao-lyQmqHsdUruS8RL5fiv_s__AOV5m4WQ?type=png)](https://mermaid.live/edit#pako:eNqFkU9LxDAQxb9KmIMobBb02IOn3sST3poi02Rqg21SJpMVWfa7Gy3-2bWscxrmPd784O3BRkdQQT_GVzsgi3qsTVBlUu6eGedB3WH_gsstRPG9tyg-Bj36JBSI9VbhjqPa6lslcfb2ummOjIl45y217RJy3qN0iTmSXPelqosTxacZxQ7EK3g_4h_Am5PnTCmPssr5n_Uc7hJDwa3Rfbu01r-VJ9c1lzUKdpjoqoUNTMQTeldK2n8EGZCBJjJQldVRj4XHgAmHYsUs8eEtWKiEM20gzw6Fao-lyQmqHsdUruS8RL5fiv_s__AOV5m4WQ)
* Kafka: a distributed streaming platform that will be used as a messaging system to pass messages between components.
* Database: a storage system that will be used to store user information, event information, and notification information.
* Multiple Spring Boot projects: this projects will contain the business logic for the notification system. 
  * notification-listener: will receive HTTP requests and produce messages to Kafka
  * notification-db-service: will consume messages from Kafka and write data to the database
  * notification-dispatcher: will consume messages from Kafka and send the notifications to the users (not implemented - requires SDK)

All of these components are Dockerized so that they can be easily deployed and managed.

Here are the high-level steps taken for creating this project:
1. Built each Spring Boot component: notification-listener, notification-db-service and notification-dispatcher.
2. Created a Dockerfile for each Spring Boot component: notification-listener, notification-db-service and notification-dispatcher.
3. Used Docker Compose to define and run a multi-container Docker application. This allowed to start all the components at once and link them together.

Overall, this project will allow you to create a scalable and efficient system for sending notifications to users based on certain events.

---

## Kafka
### Dependencies (Add to pom)
- org.springframework.kafka:spring-kafka:2.8.2
- org.apache.kafka:kafka-clients:3.1.0
- org.apache.avro:avro:2.8.2
- io.confluent:kafka-avro-serializer:5.0.0
### Avro Maven Plugin (Add to pom)
```
<!-- avro-maven-plugin -->
<plugin>
  <groupId>org.apache.avro</groupId>
  <artifactId>avro-maven-plugin</artifactId>
  <version>${avro.version}</version>
  <executions>
    <execution>
      <phase>generate-sources</phase>
      <goals>
        <goal>schema</goal>
      </goals>
      <configuration>
        <sourceDirectory>${project.basedir}/src/main/resources/avro/</sourceDirectory>
        <outputDirectory>${project.build.directory}/generated-sources/</outputDirectory>
      </configuration>
    </execution>
  </executions>
</plugin>
```
### Avro file schema (create avsc resource):
```
{
  "name": "AvroNotification",
  "type": "record",
  "namespace": "pt.itsector.notification.avro",
  "fields": [
    { "name": "notificationId", "type": "string" },
    { "name": "destination", "type": "string" },
    { "name": "subject", "type": [ "null", "string" ], "default": null },
    { "name": "message", "type": "string" },
    { "name": "creationDate", "type": [ "null", "string" ], "default": null }
  ]
}
```

## Kafka Producer (notification-listener and notification-dispatcher)
### Properties
Kafka producer minimal configuration properties:
```
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
      topic-name: notification-service
    properties:
      schema.registry.url: http://localhost:8081
```
### Implementation
With an Autowired `KafkaTemplate<String, AvroNotification>` and the topic name from `spring.kafka.producer.topic-name`, to send a message just call `send` with the avro object:
```
kafkaTemplate.send(topic, avroNotification);
```

## Kafka Receiver (notification-db-service and notification-dispatcher)
### Properties
Kafka producer minimal configuration properties:
```
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
      group-id: notification-db-consumer-group
      topic-name: notification-service
      result-topic-name: notification-result-service
    properties:
      schema.registry.url: http://localhost:8081
      specific.avro.reader: true
```
> It's important to set a group_id for each receiver!
### Implementation
You just need the annotation `KafkaListener(topics = "${spring.kafka.consumer.topic-name}", groupId = "${spring.kafka.consumer.group-id}")` to receive a message with the avro object.