package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pt.itsector.notification.avro.AvroNotification;
import pt.itsector.notification.models.Notification;
import pt.itsector.notification.models.NotificationEntity;
import pt.itsector.notification.models.NotificationResultEntity;
import pt.itsector.notification.models.Status;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationMapper extends BaseMapper {
    public static NotificationEntity mapToEntity(AvroNotification avroNotification) {
        return NotificationEntity.builder()
                .notificationId(getString(avroNotification.getNotificationId()))
                .destination(getString(avroNotification.getDestination()))
                .subject(getString(avroNotification.getSubject()))
                .message(getString(avroNotification.getMessage()))
                .creationDate(parseDate(avroNotification.getCreationDate()))
                .build();
    }

    public static Notification mapToNotification(NotificationEntity notificationEntity,
                                                 NotificationResultEntity notificationResultEntity) {
        return Notification.builder()
                .notificationId(notificationEntity.getNotificationId())
                .destination(notificationEntity.getDestination())
                .subject(notificationEntity.getSubject())
                .message(notificationEntity.getMessage())
                .creationDate(notificationEntity.getCreationDate())
                .status(getStatus(notificationResultEntity.getStatus()))
                .statusDate(notificationResultEntity.getStatusDate())
                .statusMessage(notificationResultEntity.getStatusMessage())
                .build();
    }

    private static String getStatus(Integer statusId) {
        return statusId == null ? null : Status.values()[statusId].name();
    }
}
