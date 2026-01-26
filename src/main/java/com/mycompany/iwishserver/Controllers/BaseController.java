package com.mycompany.iwishserver.Controllers;

import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;

public abstract class BaseController {
    public abstract Response handleRequest(Request request);
}