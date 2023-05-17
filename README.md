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