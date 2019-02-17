package ru.otus.hibernate.app.messages;

import ru.otus.hibernate.front.FrontendService;
import ru.otus.hibernate.app.MsgToFrontend;
import ru.otus.hibernate.messageSystem.Address;

import java.io.IOException;

public class MsgUsersToFrontend extends MsgToFrontend {
    private final String user;

    public MsgUsersToFrontend(Address from, Address to, String user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(FrontendService front) throws IOException {
        front.notifyAllUsers(user);
    }
}
