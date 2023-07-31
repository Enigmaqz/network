package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", Server.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String fuser, fserver;

            Scanner scanner = new Scanner(System.in);

            fserver = in.readLine();
            System.out.println(fserver);

            while (true) {
                fuser = scanner.nextLine();

                out.println(fuser);
                if (fuser.equals("нет")) break;
                if (fuser.equals("exit")) break;

                fserver = in.readLine();
                System.out.println(fserver);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
