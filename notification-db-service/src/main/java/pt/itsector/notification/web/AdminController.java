package pt.itsector.notification.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.itsector.notification.mappers.NotificationMapper;
import pt.itsector.notification.models.Notification;
import pt.itsector.notification.models.NotificationEntity;
import pt.itsector.notification.models.NotificationResultEntity;
import pt.itsector.notification.repository.NotificationRepository;
import pt.itsector.notification.repository.NotificationResultRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationResultRepository notificationResultRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("notifications", getNotifications());
        return "dashboard";
    }

    private List<Notification> getNotifications() {
        List<Notification> result = new ArrayList<>();

        List<NotificationEntity> notifications = (List<NotificationEntity>) notificationRepository.findAll();
        List<NotificationResultEntity> notificationsResults = (List<NotificationResultEntity>) notificationResultRepository.findAll();
        for (NotificationEntity notif : notifications) {
            NotificationResultEntity notifResult = notificationsResults
                    .stream().filter(x -> x.getNotificationId().equals(notif.getNotificationId())).findFirst()
                    .orElse(NotificationResultEntity.builder().build());
            result.add(NotificationMapper.mapToNotification(notif, notifResult));
        }

        Collections.sort(result);
        return result;
    }
}