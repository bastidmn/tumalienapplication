package de.codecommunism;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static DataInputStream input;
    private static BufferedWriter output;

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer() {
        new Thread(() -> {
            try {
                // Create a server socket
                serverSocket = new ServerSocket(55000);
                System.out.println("Server started");

                // Listen for a connection request
                Socket socket = serverSocket.accept();
                System.out.println("Connection to " + socket.getInetAddress().getHostAddress());

                // Create data input and output streams
                input = new DataInputStream(socket.getInputStream());
                output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                while (true) {
                    // Receive message from the client
                    String message = input.readUTF();
                    System.out.println(message);

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

}
