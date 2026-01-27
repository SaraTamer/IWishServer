package com.mycompany.iwishserver.network;

import com.google.gson.Gson;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private RequestProcessor processor = new RequestProcessor();
    private Gson gson = new Gson();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String jsonRequest = in.readUTF();
                Request request = gson.fromJson(jsonRequest, Request.class);

                Response response = processor.process(request);

                out.writeUTF(gson.toJson(response));
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            close();
        }
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
