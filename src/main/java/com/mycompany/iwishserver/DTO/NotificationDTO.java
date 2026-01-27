package com.mycompany.iwishserver.DTO;

public class NotificationDTO {
    private int notificationId;
    private int userId; // optional, in case we need to verify

    public int getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
