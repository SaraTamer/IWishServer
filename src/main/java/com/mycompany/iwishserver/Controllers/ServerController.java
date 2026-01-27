package com.mycompany.iwishserver.Controllers;

import com.mycompany.iwishserver.network.ServerManager;
import javafx.fxml.FXML;

public class ServerController {

    private final int PORT = 8081;

    @FXML
    private void startServer() {
        if (!ServerManager.isRunning()) {
            ServerManager.startServer(PORT);
        } else {
            System.out.println("Server is already running!");
        }
    }

    @FXML
    private void stopServer() {
        if (ServerManager.isRunning()) {
            ServerManager.stopServer();
        } else {
            System.out.println("Server is not running!");
        }
    }
}
