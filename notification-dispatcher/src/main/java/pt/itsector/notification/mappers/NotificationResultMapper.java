package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;
import pt.itsector.notification.models.Status;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResultMapper {
    public static AvroNotificationResult mapToAvroNotificationResult(final String notificationId,
                                                                     final Status status,
                                                                     final String statusMessage) {
        return AvroNotificationResult
                .newBuilder()
                .setNotificationId(notificationId)
                .setStatus(status.ordinal())
                .setStatusMessage(statusMessage)
                .setSendingDateTime(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
    }
}
