package com.mycompany.iwishserver.Controllers;

import java.io.IOException;

import com.mycompany.iwishserver.App;

import com.mycompany.iwishserver.network.Server;
import javafx.fxml.FXML;

public class ServerController {

    @FXML
    private void startServer() {

        System.out.println("Server started");
    }

    @FXML
    private void stopServer() {
        System.out.println("Server stopped");
    }
}
