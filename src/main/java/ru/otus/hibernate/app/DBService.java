package ru.otus.hibernate.app;

import ru.otus.hibernate.entity.DataSet;
import ru.otus.hibernate.messageSystem.Addressee;

public interface DBService extends Addressee {
    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(long id, Class<T> clazz);

   // <T extends DataSet> String getUserById(long id, Class<T> clazz);

    <T extends DataSet> Integer getCountUsers();

    void shutdown();
    void init();

    String getUserById(long id, Class clazz);
}
