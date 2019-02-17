package ru.otus.hibernate.dbService.service;

import ru.otus.hibernate.dbService.entity.DataSet;
import ru.otus.hibernate.dbService.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Addressee;

import java.util.List;

public interface DBService extends Addressee {
    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(long id, Class<T> clazz);

    <T extends DataSet> String getUserById(long id, Class<T> clazz);

    <T extends DataSet> Integer getCountUsers();

    void shutdown();

    void init();

    List<UserDataSet> readAllUsers();
}
