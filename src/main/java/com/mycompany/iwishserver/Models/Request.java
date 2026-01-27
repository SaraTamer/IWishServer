package com.mycompany.iwishserver.Models;

public class Request {
    private String type;    // USER, AUTH, FRIEND
    private String action;  // GET_ALL, SEND_REQUEST, UNFRIEND
    private String body;    // JSON
    private String meta; // userId, token, sessionId

    public Request(String type, String action, String body) {
        this.type = type;
        this.action = action;
        this.body = body;
    }

    public Request() {

    }

    public String getType() {
        return type;
    }

    public String getMeta() {
        return meta;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAction() {
        return action;
    }

    public String getBody() {
        return body;
    }
}
