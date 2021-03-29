package com.epam.client;

import java.io.*;
import java.net.Socket;

import static com.epam.client.Client.logger;

public class ClientLogic {
    static BufferedReader consoleReader;
    static BufferedReader readerFromServer;
    static BufferedWriter writerToServer;
    static Socket clientSocket;

    public ClientLogic(String address, int port) {
        try {
            clientSocket = new Socket(address, port);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            readerFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writerToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println(readerFromServer.readLine());
            new MessageReader().start();
            new MessageWriter().start();
            String nickname = consoleReader.readLine();
            writerToServer.write(nickname + "\n");
            writerToServer.flush();

        } catch (IOException e) {
            logger.error(e);
        }
    }
}
