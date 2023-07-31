package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 8080;
    public static void main(String[] args) {

        List<String> usedWords = new ArrayList<>();
        String lastLetter = "Z";

        try (ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("New connection accepted");
            //out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            out.println("Привет! Поиграшь в слова?");

            String input;


            while (true) {
                input = in.readLine();
                if (input.equals("нет")) break;
                if (input.equals("exit")) break;

                if (input.equals("да")) {
                    out.println("Напиши слово");
                } else {
                    if (lastLetter.equals("Z")) {
                        usedWords.add(input.toLowerCase());
                        lastLetter = input.toLowerCase().substring(input.length() - 1);
                        out.println("Ок! теперь назови слово, которое начинается на " + lastLetter + ". Или введи exit если надоело");
                    } else {
                        if (!input.toLowerCase().substring(0, 1).equals(lastLetter)) {
                            out.println("Неверно! Нужно назвать слово, которое начинается на " + lastLetter + ". Или введи exit если надоело");
                        } else if (usedWords.contains(input)) {
                            out.println("Такое слово уже было! Нужно назвать новое слово, которое начинается на " + lastLetter + ". Или введи exit если надоело");
                        } else {
                            usedWords.add(input.toLowerCase());
                            lastLetter = input.toLowerCase().substring(input.length() - 1);
                            out.println("Ок! теперь назови слово, которое начинается на " + lastLetter + ". Или введи exit если надоело");
                        }
                    }
                }
                System.out.println(input);
            }



        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}