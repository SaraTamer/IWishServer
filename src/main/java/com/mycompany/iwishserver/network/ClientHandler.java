package com.mycompany.iwishserver.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import com.mycompany.iwishserver.Models.Request;
import com.mycompany.iwishserver.Models.Response;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private RequestProcessor requestProcessor;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.requestProcessor = new RequestProcessor();
    }

    @Override
    public void run() {
        try {
            // Initialize streams
            dataIn = new DataInputStream(clientSocket.getInputStream());
            dataOut = new DataOutputStream(clientSocket.getOutputStream());

            System.out.println("Processing requests for client: " + clientSocket.getInetAddress());

            // Keep connection alive and process multiple requests
            while (!clientSocket.isClosed() && clientSocket.isConnected()) {
                try {
                    // Read request from client
                    Request request = readRequest();

                    if (request == null) {
                        // Client disconnected
                        break;
                    }

                    // Process request
                    Response response = requestProcessor.process(request);

                    // Send response back to client
                    sendResponse(response);

                } catch (EOFException e) {
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                    break;
                } catch (IOException e) {
                    System.err.println("Error communicating with client: " + e.getMessage());
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error setting up client connection: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private Request readRequest() throws IOException {
        // Read request type (e.g., LOGIN, GET_DATA, etc.)
        String requestType = dataIn.readUTF();

        // Read data length
        int dataLength = dataIn.readInt();

        // Read data
        byte[] data = new byte[dataLength];
        dataIn.readFully(data);

        // Create Request object
        return new Request(requestType, data);
    }

    private void sendResponse(Response response) throws IOException {
        // Write status
        dataOut.writeUTF(response.getStatus());

        // Write data length
        dataOut.writeInt(response.getData().length);

        // Write data
        dataOut.write(response.getData());

        // Flush to ensure data is sent
        dataOut.flush();
    }

    private void closeConnection() {
        try {
            if (dataIn != null) dataIn.close();
            if (dataOut != null) dataOut.close();
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            System.out.println("Connection closed for: " + (clientSocket != null ? clientSocket.getInetAddress() : "unknown"));
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}