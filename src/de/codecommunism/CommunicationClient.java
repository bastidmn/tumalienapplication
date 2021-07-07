package de.codecommunism;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CommunicationClient {
    private Socket socket = new Socket();
    private DataInputStream input;
    private DataOutputStream output;

    public void startClient(String hostIp) {
        try {
            socket = new Socket(hostIp, 55000);

            input = new DataInputStream(socket.getInputStream());

            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Server: " + socket.getInetAddress().getHostAddress());

            output.writeUTF("Hello");
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendShoot() throws IOException {
        if (output != null) {
            output.writeUTF("Shoot");
            output.flush();
        }

    }
}
