package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.FriendDTO;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Models.User;
import com.mycompany.iwishserver.Services.FriendsService;

import java.util.ArrayList;
import java.util.List;

public class FriendsController extends BaseController {

    private FriendsService service = new FriendsService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ======= GET ALL FRIENDS =======
                case "GET_ALL_FRIENDS": {
                    int userId = Integer.parseInt(request.getBody());
                    List<Integer> friendIds = service.getAllFriends(userId);

                    // Optional: Get full user info for each friend
                    List<User> friends = new ArrayList<>();
                    for (int id : friendIds) {
                        friends.add(service.getUserById(id));
                    }

                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(friends)
                    );
                }

                // ======= UNFRIEND =======
                case "UNFRIEND": {
                    FriendDTO dto = gson.fromJson(request.getBody(), FriendDTO.class);
                    boolean success = service.unfriend(dto.getFriendId(), dto.getUserId());

                    return new Response(
                            success ? "SUCCESS" : "ERROR",
                            success ? "Unfriended successfully" : "Cannot unfriend",
                            gson.toJson(success)
                    );
                }

                // ======= GET USER BY ID =======
                case "GET_USER": {
                    int userId = Integer.parseInt(request.getBody());
                    User user = service.getUserById(userId);
                    if (user != null) {
                        return new Response(
                                "SUCCESS",
                                null,
                                gson.toJson(user)
                        );
                    } else {
                        return new Response("ERROR", "User not found", null);
                    }
                }

                default:
                    return new Response("ERROR", "Unknown FRIENDS action", null);
            }
        } catch (Exception e) {
            return new Response("ERROR", e.getMessage(), null);
        }
    }
}
