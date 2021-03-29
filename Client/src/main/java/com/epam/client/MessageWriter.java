package com.epam.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.client.Client.logger;

public class MessageWriter extends Thread {
    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss   ");
        try {
            while (true) {
                String userInput = ClientLogic.consoleReader.readLine();
                if (userInput.equals("exit")) {
                    ClientLogic.clientSocket.close();
                    ClientLogic.readerFromServer.close();
                    ClientLogic.writerToServer.close();
                    break;
                }
                ClientLogic.writerToServer.write(simpleDateFormat.format(new Date()) + userInput + "\n");
                ClientLogic.writerToServer.flush();
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
