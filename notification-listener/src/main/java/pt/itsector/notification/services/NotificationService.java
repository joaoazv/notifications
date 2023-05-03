package pt.itsector.notification.services;

import pt.itsector.notification.models.NotificationRequest;

public interface NotificationService {
    String sendNotification(NotificationRequest request);
}
