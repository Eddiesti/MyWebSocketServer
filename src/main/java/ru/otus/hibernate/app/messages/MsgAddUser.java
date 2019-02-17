package ru.otus.hibernate.app.messages;

import com.google.gson.Gson;
import ru.otus.hibernate.dbService.service.DBService;
import ru.otus.hibernate.app.MsgToDB;
import ru.otus.hibernate.dbService.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Address;

public class MsgAddUser extends MsgToDB {

    public String name;

    public MsgAddUser(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    @Override
    public void exec(DBService dbService) {
        Gson gson = new Gson();
        UserDataSet user = gson.fromJson(name, UserDataSet.class);
        dbService.save(user);

        dbService.getMS().sendMessage(new MsgUsersToFrontend(getTo(), getFrom(), user.toString()));
        dbService.getMS().sendMessage(new MsgUsersToFrontend(getFrom(), getTo(), name));

    }
}
