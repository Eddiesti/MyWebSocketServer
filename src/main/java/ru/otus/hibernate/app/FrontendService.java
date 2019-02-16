package ru.otus.hibernate.app;

import ru.otus.hibernate.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.MessageSystem;
import ru.otus.hibernate.websocket.UserWebSocket;

import java.io.IOException;

public interface FrontendService extends Addressee {
    void init();
    void sendAddUser(String user) throws IOException;
    void notifyAllUsers(String name);
    MessageSystem getMS();
    void removeClient(Integer id);
    Integer addClient(UserWebSocket webSocket);
}
