package com.mycompany.iwishserver.Models;

public class Response {
    private String status;   // SUCCESS / ERROR
    private String message;  // optional
    private String body;     // JSON

    public Response(String status, String message, String body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getBody() {
        return body;
    }
}
