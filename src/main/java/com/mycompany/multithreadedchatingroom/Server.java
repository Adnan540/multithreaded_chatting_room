package com.mycompany.multithreadedchatingroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    // Constructor
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // Start server method
    public void startServer() {
        try {
            // While server is running
            while (!serverSocket.isClosed()) {
                // Wait for client to connect
                Socket socket = serverSocket.accept();
                System.out.println("A new Client has connected ");
                // Spawn a new thread to handle client request
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Close server method
    public void closeServer() {
        try {
            serverSocket.close();
            System.out.println("Server has been closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234); // Server listening on port 1234
            Server server = new Server(serverSocket);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
