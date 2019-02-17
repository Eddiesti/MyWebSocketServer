package ru.otus.hibernate.app.messages;

import com.google.gson.Gson;
import ru.otus.hibernate.dbService.service.DBService;
import ru.otus.hibernate.app.MsgToDB;
import ru.otus.hibernate.dbService.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Address;

import java.util.List;

public class MsgAddUser extends MsgToDB {

    public String user;

    public MsgAddUser(Address from, Address to, String user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(DBService dbService) {
        Gson gson = new Gson();
        UserDataSet userSet = gson.fromJson(user, UserDataSet.class);
        dbService.save(userSet);
        List<UserDataSet> allUsers = dbService.readAllUsers();
        dbService.getMS().sendMessage(new MsgUsersToFrontend(getTo(), getFrom(),allUsers.toString()));
    }
}
