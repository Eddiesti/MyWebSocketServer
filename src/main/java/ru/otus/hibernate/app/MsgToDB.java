package ru.otus.hibernate.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.Message;


public abstract  class MsgToDB extends Message {
    private Logger log = LoggerFactory.getLogger(MsgToDB.class);

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        log.trace("exec(): {}", addressee);
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
