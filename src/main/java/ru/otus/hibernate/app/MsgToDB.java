package ru.otus.hibernate.app;


import ru.otus.hibernate.dbService.service.DBService;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.Addressee;
import ru.otus.hibernate.messageSystem.Message;


public abstract class MsgToDB extends Message {

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {

        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
