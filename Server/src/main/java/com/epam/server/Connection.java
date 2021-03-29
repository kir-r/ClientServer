package com.epam.server;

import java.io.*;
import java.net.Socket;

import static com.epam.server.Server.logger;

public class Connection extends Thread {
    private Socket socket;

    private BufferedReader inputStream;
    private BufferedWriter outputStream;

    public Connection(Socket socket) throws IOException {
        logger.info("Created a new connection");
        this.socket = socket;
        inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        if (!FiveLastMessages.listOfFiveMessages.isEmpty()) {
            FiveLastMessages.sendFiveLastMessages(outputStream);
            logger.info("Send Five Last Messages - done");
        }
        start();
    }

    @Override
    public void run() {
        try {
            try {
                outputStream.write("Type your nickname" + "\n");
                outputStream.flush();
                String nickname = inputStream.readLine();

                while (true) {
                    String message = inputStream.readLine();
                    if (message.equalsIgnoreCase("exit")) { //TODO error?
                        threadBreak();
                        break;
                    }

                    logger.info("Incoming message from " + nickname + ": " + message);
                    FiveLastMessages.addLastFiveMessages(nickname + ": " + message);
                    CashOfMessages.saveMessagesInFile(nickname + ": " + message);

                    for (Connection connection : Server.listOfConnection) { // отправить сообщение всем в чате
                        if (connection.equals(this)) { // не отправлять сообщение тому кто его написал
                            continue;
                        }
                        connection.send(nickname + ": " + message);
                    }
                }
            } catch (NullPointerException e) {
                logger.error("Client finished conversation.\n" + e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            logger.error("Client disconnected.\n" + e);
        }
    }

    private void send(String message) {
        try {
            outputStream.write(message + "\n");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void threadBreak() {
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
            for (Connection connection : Server.listOfConnection) {
                if (connection.equals(this)) { //TODO visualVM test, why do I need interrupt?
                    connection.interrupt();
                }
                Server.listOfConnection.remove(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
