package com.epam.client;

import java.io.IOException;

import static com.epam.client.Client.logger;

class MessageReader extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                String readFromServer = ClientLogic.readerFromServer.readLine();
                if (readFromServer.equals("exit")) { //TODO error?
                    ClientLogic.clientSocket.close();
                    ClientLogic.readerFromServer.close();
                    ClientLogic.writerToServer.close();
                    break;
                }
                System.out.println(readFromServer);
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
