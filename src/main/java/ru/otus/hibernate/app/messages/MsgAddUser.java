package ru.otus.hibernate.app.messages;

import ru.otus.hibernate.app.DBService;
import ru.otus.hibernate.app.MsgToDB;
import ru.otus.hibernate.entity.DataSet;
import ru.otus.hibernate.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Address;

public class MsgAddUser extends MsgToDB {

    public String name;

    public MsgAddUser(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    @Override
    public void exec(DBService dbService) {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        dbService.save(user);
        dbService.getMS().sendMessage(new MsgUserToFrontend(getFrom(), getTo(), name));
        System.out.println("hello");
    }
}
