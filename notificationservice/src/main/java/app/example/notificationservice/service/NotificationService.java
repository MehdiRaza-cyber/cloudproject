package app.example.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(String message) {
        // Logic to send notification
        System.out.println("Notification sent: " + message);
    }
}
