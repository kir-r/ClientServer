package com.epam.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.log4j.*;

import static com.epam.server.Server.logger;

public class FiveLastMessages {
    static ArrayList<String> listOfFiveMessages = new ArrayList<>(); //ArrayBlockingQueue

    static Logger secondLogger = LogManager.getLogger("secondLogger");

    public static void addLastFiveMessages(String message) { //TODO  log4j appender

        secondLogger.info(message);

        listOfFiveMessages.add(message);
        if (listOfFiveMessages.size() > 5) {
            listOfFiveMessages.remove(0);
        }
    }

    public static void sendFiveLastMessages(BufferedWriter writer) {
        try {
            writer.write("Here's last five messages: " + "\n");
            for (String message : listOfFiveMessages) {
                writer.write(message + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
