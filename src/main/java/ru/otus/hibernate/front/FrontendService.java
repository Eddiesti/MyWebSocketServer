package ru.otus.hibernate.front;

import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.MessageSystem;
import ru.otus.hibernate.websocket.UserWebSocket;

import java.io.IOException;

public interface FrontendService extends Addressee {

    void sendAddUser(String user) throws IOException;

    void notifyAllUsers(String name);

    MessageSystem getMS();

    void removeClient(String id);

    String addClient(UserWebSocket webSocket);

    //void sendGetUsersList();
}
