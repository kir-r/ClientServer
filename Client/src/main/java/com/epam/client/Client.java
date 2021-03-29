package com.epam.client;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Client {
    static final Logger logger = LogManager.getLogger(Client.class);

    public static void main(String[] args) {
        BasicConfigurator.configure(); //without it logger doesn't work?
        new ClientLogic("localhost", 1);
    }
}
//Use UI - QT or ElectronJS instead of swing