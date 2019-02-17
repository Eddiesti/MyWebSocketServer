package ru.otus.hibernate.app;

import ru.otus.hibernate.front.FrontendService;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.Message;

import java.io.IOException;

public abstract class MsgToFrontend extends Message {


    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) throws IOException {

        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        } else {
            //todo error!
        }
    }

    public abstract void exec(FrontendService front) throws IOException;
}