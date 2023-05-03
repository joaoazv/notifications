package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;
import pt.itsector.notification.models.DBNotification;
import pt.itsector.notification.models.Status;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBNotificationMapper extends BaseMapper {
    public static DBNotification mapToDBNotification(AvroNotification avroNotification) {
        return DBNotification.builder()
                .notificationId(getString(avroNotification.getNotificationId()))
                .status(Status.PENDING.ordinal())
                .destination(getString(avroNotification.getDestination()))
                .subject(getString(avroNotification.getSubject()))
                .message(getString(avroNotification.getMessage()))
                .creationDateTime(parseDate(avroNotification.getCreationDateTime()))
                .sendingDateTime(null)
                .build();
    }
}
