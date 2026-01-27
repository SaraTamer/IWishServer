package com.mycompany.iwishserver.network;

public class ServerManager {

    private static Server server;
    private static Thread serverThread;

    public static void startServer(int port) {
        if (serverThread != null && serverThread.isAlive()) {
            System.out.println("Server already running!");
            return;
        }

        server = new Server(port);

        serverThread = new Thread(() -> {
            server.start();
        });

        serverThread.setDaemon(true); // close when app closes
        serverThread.start();

        System.out.println("Server started from GUI!");
    }

    public static void stopServer() {
        if (server != null) {
            server.stop();
            System.out.println("Server stopped from GUI!");
        }
    }

    public static boolean isRunning() {
        return serverThread != null && serverThread.isAlive();
    }
}
