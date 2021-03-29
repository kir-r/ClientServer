package com.epam.client;

import java.io.IOException;

class MessageReader extends Thread {
    @Override
    public void run() {
        try {
            while(true) {
                String readFromServer = SecondClientLogic.readerFromServer.readLine();
                if (readFromServer.equals("exit")) {
                    SecondClientLogic.clientSocket.close();
                    SecondClientLogic.readerFromServer.close();
                    SecondClientLogic.writerToServer.close();
                    break;
                }
                System.out.println(readFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
