package com.mycompany.iwishserver.Models;

public class Response {
    private String status; // SUCCESS, ERROR, etc.
    private byte[] data;

    public Response(String status, byte[] data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public byte[] getData() {
        return data;
    }
}