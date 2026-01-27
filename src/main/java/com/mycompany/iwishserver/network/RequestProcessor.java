package com.mycompany.iwishserver.network;

import com.mycompany.iwishserver.Controllers.*;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;

import java.util.HashMap;
import java.util.Map;

public class RequestProcessor {

    private Map<String, BaseController> controllers = new HashMap<>();

    public RequestProcessor() {
        controllers.put("USER", new UserController());
        controllers.put("CONTRIBUTION", new ContributionController());
        controllers.put("FRIEND_REQUEST", new FriendRequestController());
        controllers.put("Gifts", new GiftController());
        controllers.put("ALL_USERS", new AllUsersController());
        controllers.put("FRIENDS", new FriendsController());
        controllers.put("NOTIFICATION", new NotificationController());
    }

    public Response process(Request request) {
        BaseController controller = controllers.get(request.getType());

        if (controller == null) {
            return new Response(
                    "ERROR",
                    "Unknown request type",
                    null
            );
        }

        return controller.handle(request);
    }
}
