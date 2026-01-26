package com.mycompany.iwishserver.network;

import com.mycompany.iwishserver.Controllers.BaseController;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import java.util.HashMap;
import java.util.Map;

public class RequestProcessor {
    private Map<String, BaseController> controllers;

    public RequestProcessor() {
        controllers = new HashMap<>();
        // Initialize your controllers here
        // controllers.put("USER", new UserController());
        // controllers.put("PRODUCT", new ProductController());
    }

    public Response process(Request request) {
        String requestType = request.getType();

        // Route to appropriate controller based on request type
        BaseController controller = controllers.get(requestType);

        if (controller != null) {
            return controller.handleRequest(request);
        } else {
            // Return error response if no controller found
            return new Response("ERROR", ("Unknown request type: " + requestType).getBytes());
        }
    }
}