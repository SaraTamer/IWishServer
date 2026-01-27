package com.mycompany.iwishserver.Controllers;

import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;
import com.mycompany.iwishserver.Services.UserService;

public abstract class BaseController {
    public abstract Response handle(Request request);
}