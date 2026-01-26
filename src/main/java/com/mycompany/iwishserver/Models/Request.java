package com.mycompany.iwishserver.Models;

public class Request {
    private String type;
    private byte[] data;

    public Request(String type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public String getDataAsString() {
        return new String(data);
    }
}