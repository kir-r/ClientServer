package com.epam.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageWriter extends Thread {
    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss   ");
        try {
            while (true) {
                String userInput = SecondClientLogic.consoleReader.readLine();
                if (userInput.equals("exit")) {
                    SecondClientLogic.clientSocket.close();
                    SecondClientLogic.readerFromServer.close();
                    SecondClientLogic.writerToServer.close();
                    break;
                }
                SecondClientLogic.writerToServer.write(simpleDateFormat.format(new Date()) + userInput + "\n");
                SecondClientLogic.writerToServer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
