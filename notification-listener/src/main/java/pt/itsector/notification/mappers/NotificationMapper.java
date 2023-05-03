package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.models.NotificationRequest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMapper {
    public static AvroNotification mapToAvroNotification(final String notificationId,
                                                                                       final NotificationRequest request) {
        return AvroNotification
                .newBuilder()
                .setNotificationId(notificationId)
                .setDestination(request.getDestination())
                .setSubject(request.getSubject())
                .setMessage(request.getMessage())
                .setCreationDateTime(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
    }
}
