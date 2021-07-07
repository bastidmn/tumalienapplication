package de.codecommunism;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Controller {
    CommunicationClient client = new CommunicationClient();

    public boolean connect(String ip) {
        String verifiedIp = verifyIp(ip);
        if (verifiedIp != null) {
            System.out.println("Connect to: " + verifiedIp);
            client.startClient(verifiedIp);
        }
        return false;
    }

    //From Arnaldo Ignacio Gaspar Véjar @ https://stackoverflow.com/questions/23847379/checking-if-a-string-contains-an-ip
    //Modified
    private String verifyIp(String ip) {
        System.out.println(ip);
        if (ip == null) {
            return null;
        } else if (ip.equals("localhost")) {
            try {
                return Inet4Address.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        String[] numbers = ip.split("\\.");

        if (numbers.length != 4) return null;

        for (String number : numbers) {
            int value = Integer.parseInt(number);
            if (value <= 0 || value >= 255) {
                return null;
            }
        }
        return ip;
    }
}
