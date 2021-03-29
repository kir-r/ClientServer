package com.epam.client;

import java.io.*;
import java.net.Socket;

public class SecondClientLogic {
    static BufferedReader consoleReader;
    static BufferedReader readerFromServer;
    static BufferedWriter writerToServer;
    static Socket clientSocket;

    public SecondClientLogic(String address, int port) {
        try {
            clientSocket = new Socket(address, port);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            readerFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writerToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println(readerFromServer.readLine());
//            System.out.println("Type your nickname");
            new MessageReader().start();
            new MessageWriter().start();
            String nickname = consoleReader.readLine();
            writerToServer.write(nickname + "\n");
            writerToServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
