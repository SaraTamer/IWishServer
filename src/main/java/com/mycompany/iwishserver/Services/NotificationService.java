package com.mycompany.iwishserver.Services;

import com.mycompany.iwishserver.DAO.NotificationDAO;
import com.mycompany.iwishserver.Models.Notification;

import java.util.List;

public class NotificationService {


    // Get all notifications
    public List<Notification> getAllNotifications(int currentUserId) {

        NotificationDAO notificationDAO = new NotificationDAO();

        List<Notification> notifications = notificationDAO.getAllNotificationsByUserId(currentUserId);

        // Check if there is notifications
        if (notifications.isEmpty()){
            System.out.println("Service Not Found");
            return null;
        }

        return notifications;

    }

    // Mark a notification as read
    public boolean markNotificationAsRead(int notificationId) {

        // Mark notification as read
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.markAsRead(notificationId);
    }
}
