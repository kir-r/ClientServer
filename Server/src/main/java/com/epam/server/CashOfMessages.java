package com.epam.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashOfMessages {
    private static PrintWriter printWriter;

    static {
        try {
            printWriter = new PrintWriter("Cash of messages.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PrintWriter getPrintWriter() {
        return printWriter;
    }

    static void saveMessagesInFile(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");
        printWriter.write(simpleDateFormat.format(new Date()) + message + "\n");
        printWriter.flush();
    }
}
