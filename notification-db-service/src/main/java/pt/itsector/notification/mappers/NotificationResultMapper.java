package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.avro.AvroNotificationResult;
import pt.itsector.notification.models.NotificationEntity;
import pt.itsector.notification.models.NotificationResultEntity;
import pt.itsector.notification.models.Status;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResultMapper extends BaseMapper {
    public static NotificationResultEntity mapToEntity(AvroNotificationResult avro) {
        return NotificationResultEntity.builder()
                .notificationId(getString(avro.getNotificationId()))
                .status(avro.getStatus())
                .statusDate(parseDate(avro.getStatusDate()))
                .statusMessage(getString(avro.getStatusMessage()))
                .build();
    }
}
