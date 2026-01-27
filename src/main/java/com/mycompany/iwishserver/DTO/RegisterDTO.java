package com.mycompany.iwishserver.DTO;

public class RegisterDTO {
    public String userName;
    public String email;
    public String password;
    public String rePassword;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRePassword() { return rePassword; }
}
