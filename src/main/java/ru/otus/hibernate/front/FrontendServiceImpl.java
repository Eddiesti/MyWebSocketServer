package ru.otus.hibernate.front;


import ru.otus.hibernate.app.FrontendService;
import ru.otus.hibernate.app.MessageSystemContext;
import ru.otus.hibernate.app.messages.MsgAddUser;
import ru.otus.hibernate.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.*;
import ru.otus.hibernate.websocket.UserWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import  java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FrontendServiceImpl  implements FrontendService {
    private  Address address;
    private ConcurrentHashMap<Integer,UserWebSocket> map;
    private  MessageSystemContext context;

    public FrontendServiceImpl(Address address,  MessageSystemContext context) {
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
    public void sendAddUser(String name)  {
        context.getMessageSystem().sendMessage(new MsgAddUser(this.address, context.getDbAddress(),name));
    }

    public void notifyAllUsers(String name)  {
        for(UserWebSocket item : map.values()){
            try {
                item.getSession().getRemote().sendString(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer addClient(UserWebSocket webSocket) {
        Integer id = random();
        this.map.put(id,webSocket);
        return null;
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
        return  context.getMessageSystem();
    }

    private int random(){
        final int min = 1;
        final int max = 100;
        return  min + (int)(Math.random() * max);
    }
}
