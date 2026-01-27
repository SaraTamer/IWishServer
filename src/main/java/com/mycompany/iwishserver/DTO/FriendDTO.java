package com.mycompany.iwishserver.DTO;

public class FriendDTO {
    private int userId;       // current user
    private int friendId;     // friend to unfriend (optional)

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
