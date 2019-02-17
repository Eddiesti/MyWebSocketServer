package ru.otus.hibernate.front;


import ru.otus.hibernate.app.MessageSystemContext;
import ru.otus.hibernate.app.messages.MsgAddUser;
import ru.otus.hibernate.messageSystem.*;
import ru.otus.hibernate.websocket.UserWebSocket;

import java.io.IOException;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FrontendServiceImpl implements FrontendService {
    private Address address;
    private ConcurrentHashMap<String, UserWebSocket> map;
    private MessageSystemContext context;

    public FrontendServiceImpl(Address address, MessageSystemContext context) {
        this.address = address;
        this.map = new ConcurrentHashMap<>();
        this.context = context;
    }

    @Override
    public void sendAddUser(String user) {
        context.getMessageSystem().sendMessage(new MsgAddUser(this.getAddress(), context.getDbAddress(),user));

    }

    public void notifyAllUsers(String user) {
        for (UserWebSocket item : map.values()) {
            try {
                item.getSession().getRemote().sendString(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String addClient(UserWebSocket webSocket) {
        String id = UUID.randomUUID().toString();
        this.map.put(id, webSocket);
        return id;
    }

    public void removeClient(String id) {
        this.map.remove(id);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

}
