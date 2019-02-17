package ru.otus.hibernate.dbService.dao;

import org.hibernate.Session;

import ru.otus.hibernate.dbService.entity.DataSet;
import ru.otus.hibernate.dbService.entity.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;

    }

    public <T extends DataSet> void save(T user) {
        session.save(user);
    }

    public <T extends DataSet> T load(Class<T> clazz, long id) {
        return session.load(clazz, id);
    }

    public <T extends DataSet> String getUserById(long id, Class<T> clazz) {
        T user = session.load(clazz, id);
        return user.toString();
    }

    public <T extends DataSet> Integer getCountUsers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list().size();
    }

    public List<UserDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }

}
