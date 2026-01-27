package com.mycompany.iwishserver.Controllers;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.LoginDTO;
import com.mycompany.iwishserver.DTO.RegisterDTO;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Models.User;
import com.mycompany.iwishserver.Services.UserService;

public class UserController extends BaseController {

    private UserService userService = new UserService();
    private Gson gson = new Gson();

    @Override
    public Response handle(Request request) {
        try {
            switch (request.getAction()) {

                // ================= REGISTER =================
                case "REGISTER": {
                    RegisterDTO dto =
                            gson.fromJson(request.getBody(), RegisterDTO.class);

                    User user = new User();
                    user.setUserName(dto.getUserName());
                    user.setMail(dto.getEmail());
                    user.setPass(dto.getPassword());

                    String result =
                            userService.register(user, dto.getRePassword());

                    return new Response(
                            result.equals("SUCCESS") ? "SUCCESS" : "ERROR",
                            result,
                            null
                    );
                }

                // ================= LOGIN =================
                case "LOGIN": {
                    LoginDTO dto =
                            gson.fromJson(request.getBody(), LoginDTO.class);

                    User user =
                            userService.loginAndGetUser(
                                    dto.getEmail(),
                                    dto.getPassword()
                            );

                    if (user == null) {
                        return new Response(
                                "ERROR",
                                "Invalid email or password",
                                null
                        );
                    }

                    return new Response(
                            "SUCCESS",
                            "Login successful",
                            gson.toJson(user)
                    );
                }

                // ================= GET USER BY ID =================
                case "GET_BY_ID": {
                    int userId = Integer.parseInt(request.getBody());

                    User user = userService.getUserById(userId);

                    if (user == null) {
                        return new Response(
                                "ERROR",
                                "User not found",
                                null
                        );
                    }

                    return new Response(
                            "SUCCESS",
                            null,
                            gson.toJson(user)
                    );
                }

                default:
                    return new Response(
                            "ERROR",
                            "Unknown USER action",
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
