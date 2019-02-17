package ru.otus.hibernate.app.messages;

import ru.otus.hibernate.front.FrontendService;
import ru.otus.hibernate.app.MsgToFrontend;
import ru.otus.hibernate.messageSystem.Address;

import java.io.IOException;

public class MsgUsersToFrontend extends MsgToFrontend {
    private final String name;

    public MsgUsersToFrontend(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    @Override
    public void exec(FrontendService front) throws IOException {
        front.notifyAllUsers(name);
    }
}