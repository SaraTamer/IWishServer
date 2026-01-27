package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.FriendActionDTO;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.AllUsersService;

import java.util.List;

public class AllUsersController extends BaseController {

    private AllUsersService service = new AllUsersService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ========== A. GET ALL USERS ==========
                case "GET_ALL_USERS": {
                    int currentUserId = Integer.parseInt(request.getBody());

                    List<String> users = service.getAllUsersWithFriendshipStatus(currentUserId);

                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(users)
                    );
                }

                // ========== B. SEND FRIEND REQUEST ==========
                case "SEND_REQUEST": {
                    FriendActionDTO dto = gson.fromJson(request.getBody(), FriendActionDTO.class);

                    boolean success = service.sendFriendRequest(dto.getReceiverId(), dto.getSenderId());

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Friend request sent" : "Cannot send friend request",
                            gson.toJson(success)
                    );
                }

                // ========== C. UNFRIEND ==========
                case "UNFRIEND": {
                    FriendActionDTO dto = gson.fromJson(request.getBody(), FriendActionDTO.class);

                    boolean success = service.unfriend(dto.getReceiverId(), dto.getSenderId());

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Unfriended successfully" : "Cannot unfriend",
                            gson.toJson(success)
                    );
                }

                default:
                    return new Response(
                            "ERROR",
                            "Unknown ALL_USERS action",
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
