package ru.otus.hibernate.app.messages;

import com.google.gson.Gson;
import ru.otus.hibernate.app.MsgToDB;
import ru.otus.hibernate.dbService.entity.UserDataSet;
import ru.otus.hibernate.dbService.service.DBService;
import ru.otus.hibernate.messageSystem.Address;

import java.util.List;

public class MsgLoadUser extends MsgToDB {
    public MsgLoadUser(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(DBService dbService) {
        Gson gson = new Gson();
        List<UserDataSet> userDataSets = dbService.readAll();
        dbService.getMS().sendMessage(new MsgUsersToFrontend(getTo(), getFrom(), gson.toJson(userDataSets)));
    }
}
