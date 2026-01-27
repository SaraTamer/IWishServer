package com.mycompany.iwishserver.Test;

import com.google.gson.Gson;
import com.mycompany.iwishserver.DTO.LoginDTO;
import com.mycompany.iwishserver.DTO.RegisterDTO;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketTestClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8081; // replace with your server port

        try (Socket socket = new Socket(host, port);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            Gson gson = new Gson();

            // ========== TEST REGISTER ==========
            RegisterDTO registerDTO = new RegisterDTO();
            registerDTO.setUserName("AliSocket");
            registerDTO.setEmail("ali.socket@test.com");
            registerDTO.setPassword("123456");
            registerDTO.setRePassword("123456");

            Request registerRequest = new Request();
            registerRequest.setType("USER");
            registerRequest.setAction("REGISTER");
            registerRequest.setBody(gson.toJson(registerDTO));

            out.writeUTF(gson.toJson(registerRequest));
            out.flush();

            Response registerResponse = gson.fromJson(in.readUTF(), Response.class);
            System.out.println("REGISTER RESPONSE: " + registerResponse.getMessage());

            // ========== TEST LOGIN ==========
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setEmail("ali.socket@test.com");
            loginDTO.setPassword("123456");

            Request loginRequest = new Request();
            loginRequest.setType("USER");
            loginRequest.setAction("LOGIN");
            loginRequest.setBody(gson.toJson(loginDTO));

            out.writeUTF(gson.toJson(loginRequest));
            out.flush();

            Response loginResponse = gson.fromJson(in.readUTF(), Response.class);
            System.out.println("LOGIN RESPONSE: " + loginResponse.getMessage());
            System.out.println("LOGIN USER DATA: " + loginResponse.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}