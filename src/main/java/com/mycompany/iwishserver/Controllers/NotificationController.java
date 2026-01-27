package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.NotificationDTO;
import com.mycompany.iwishserver.Models.Notification;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.NotificationService;

import java.util.List;

public class NotificationController extends BaseController {

    private NotificationService service = new NotificationService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ========== GET ALL NOTIFICATIONS ==========
                case "GET_ALL_NOTIFICATIONS": {
                    int userId = Integer.parseInt(request.getBody());
                    List<Notification> notifications = service.getAllNotifications(userId);
                    if (notifications == null) {
                        return new Response("ERROR", "No notifications found", null);
                    }
                    return new Response("SUCCESS", null, gson.toJson(notifications));
                }

                // ========== MARK NOTIFICATION AS READ ==========
                case "MARK_AS_READ": {
                    NotificationDTO dto = gson.fromJson(request.getBody(), NotificationDTO.class);
                    boolean success = service.markNotificationAsRead(dto.getNotificationId());
                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Notification marked as read" : "Failed to mark as read",
                            null
                    );
                }

                default:
                    return new Response("ERROR", "Unknown NOTIFICATION action", null);
            }
        } catch (Exception e) {
            return new Response("ERROR", e.getMessage(), null);
        }
    }
}
