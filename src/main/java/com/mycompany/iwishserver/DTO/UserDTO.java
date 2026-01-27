package com.mycompany.iwishserver.DTO;

public class UserDTO {
    private int userId;
    private String userName;
    private String mail;

    public UserDTO() {}

    public UserDTO(int userId, String userName, String mail) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
// getters & setters
}
