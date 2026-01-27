package com.mycompany.iwishserver.DTO;

public class LoginDTO {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
