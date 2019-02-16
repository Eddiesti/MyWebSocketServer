package ru.otus.hibernate.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hibernate.front.FrontendServiceImpl;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.Message;

import java.io.IOException;

public abstract class MsgToFrontend extends Message {

    private Logger log = LoggerFactory.getLogger(MsgToFrontend.class);

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) throws IOException {
        log.trace("exec(): {}", addressee);
        if (addressee instanceof FrontendServiceImpl) {
            exec((FrontendServiceImpl) addressee);
        } else {
            //todo error!
        }
    }

    public abstract void exec(FrontendService front) throws IOException;
}