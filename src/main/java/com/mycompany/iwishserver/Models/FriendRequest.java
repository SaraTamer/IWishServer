package com.mycompany.iwishserver.Models;


public class FriendRequest {

    private int reqId;          // maps to req_id
    private int senderId;       // maps to sender_id
    private int receiverId;     // maps to receiver_id
    private String status;      // 'PENDING', 'ACCEPTED', 'DECLINED'

    // Constructors
    public FriendRequest() {}

    public FriendRequest(int senderId, int receiverId, String status) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    // Getters and Setters
    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
