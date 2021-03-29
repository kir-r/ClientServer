package com.epam.server;

/**Задача:
 Написать приложение для обмена текстовыми сообщениями.
 Требования:
 Приложение состоит из клиента (у каждого участника чата) и сервера
 При подключении сервер запрашивает nickname клиента
 История сообщений должна хранится в файле, чтобы при рестарте сервера не терялась история сообщений
 При входе клиент видит пять последних сообщений в чате
 Настройки подключения должны находится в отдельном файле .properties
 Код должен быть покрыт юнит тестами
 Разработку вести в TDD манере
 Код должен находится в git репозитории
 Проект должен быть maven проектом
 Использовать log4j*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Server {
    static ArrayList<Connection> listOfConnection = new ArrayList<>();
    static final Logger logger = LogManager.getLogger(Server.class);
    //Если в логгере указано имя класса, то используется рут логгер (по умолчанию),
    //его можно настраивать, но не обязательно.

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure(); //without it logger doesn't work?
        try (ServerSocket serverSocket = new ServerSocket(1)) {
            logger.info("Server is ready to accept");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("Accepted");
                try {
                    listOfConnection.add(new Connection(socket));
                } catch (IOException e) {
                    logger.error(e);
                    socket.close();
                    CashOfMessages.getPrintWriter().close();
                }
            }
        }
    }
}