package ru.otus.hibernate.front;


import ru.otus.hibernate.app.MessageSystemContext;
import ru.otus.hibernate.app.messages.MsgAddUser;
import ru.otus.hibernate.app.messages.MsgLoadUser;
import ru.otus.hibernate.messageSystem.*;
import ru.otus.hibernate.websocket.UserWebSocket;

import java.io.IOException;

import java.util.concurrent.ConcurrentHashMap;

public class FrontendServiceImpl implements FrontendService {
    private Address address;
    private ConcurrentHashMap<Integer, UserWebSocket> map;
    private MessageSystemContext context;

    public FrontendServiceImpl(Address address, MessageSystemContext context) {
        this.address = address;
        this.map = new ConcurrentHashMap<>();
        this.context = context;
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setFrontAddress(this.address);
    }

    @Override
    public void sendAddUser(String data) {
        context.getMessageSystem().sendMessage(new MsgAddUser(this.getAddress(), context.getDbAddress(), data));
    }

    public void notifyAllUsers(String data) {
        for (UserWebSocket item : map.values()) {
            try {
                item.getSession().getRemote().sendString(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer addClient(UserWebSocket webSocket) {
        Integer id = random();
        this.map.put(id, webSocket);
        return id;
    }
    @Override
    public void sendGetUsersList() {
        context.getMessageSystem().sendMessage(new MsgLoadUser(this.getAddress(),context.getDbAddress()));
    }

    public void removeClient(Integer id) {
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

    private int random() {
        final int MIN = 1;
        final int MAX = 100;
        return MIN + (int) (Math.random() * MAX);
    }
}
