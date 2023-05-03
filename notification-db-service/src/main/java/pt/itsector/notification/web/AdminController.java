package pt.itsector.notification.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.itsector.notification.models.DBNotification;
import pt.itsector.notification.repository.NotificationRepository;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        List<DBNotification> notifications = (List<DBNotification>) notificationRepository.findAll();
        model.addAttribute("notifications", notifications);
        return "dashboard";
    }
}