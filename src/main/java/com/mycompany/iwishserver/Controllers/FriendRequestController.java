package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.FriendRequestActionDTO;
import com.mycompany.iwishserver.Models.FriendRequest;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.FriendRequestsService;

import java.util.List;

public class FriendRequestController extends BaseController {

    private FriendRequestsService service = new FriendRequestsService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ========== A. GET PENDING REQUESTS ==========
                case "GET_PENDING": {
                    int userId = Integer.parseInt(request.getBody());

                    List<FriendRequest> requests =
                            service.getPendingFriendRequests(userId);

                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(requests)
                    );
                }

                // ========== B. ACCEPT REQUEST ==========
                case "ACCEPT": {
                    FriendRequestActionDTO dto =
                            gson.fromJson(request.getBody(), FriendRequestActionDTO.class);

                    boolean success =
                            service.acceptRequest(
                                    dto.getRequestId(),
                                    dto.getCurrentUserId()
                            );

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Request accepted" : "Cannot accept request",
                            gson.toJson(success)
                    );
                }

                // ========== C. DECLINE REQUEST ==========
                case "DECLINE": {
                    FriendRequestActionDTO dto =
                            gson.fromJson(request.getBody(), FriendRequestActionDTO.class);

                    boolean success =
                            service.declineRequest(
                                    dto.getRequestId(),
                                    dto.getCurrentUserId()
                            );

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Request declined" : "Cannot decline request",
                            gson.toJson(success)
                    );
                }

                default:
                    return new Response(
                            "ERROR",
                            "Unknown FRIEND_REQUEST action",
                            null
                    );
            }

        } catch (Exception e) {
            return new Response(
                    "ERROR",
                    e.getMessage(),
                    null
            );
        }
    }
}
