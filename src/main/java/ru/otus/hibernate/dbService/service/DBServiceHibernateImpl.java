package ru.otus.hibernate.dbService.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.hibernate.dbService.config.HibernateConfiguration;
import ru.otus.hibernate.dbService.dao.UsersDAO;
import ru.otus.hibernate.dbService.entity.DataSet;
import ru.otus.hibernate.dbService.entity.UserDataSet;
import ru.otus.hibernate.messageSystem.Address;
import ru.otus.hibernate.messageSystem.MessageSystem;
import ru.otus.hibernate.app.MessageSystemContext;

import java.util.List;

public class DBServiceHibernateImpl implements DBService {
    private final SessionFactory sessionFactory;
    private MessageSystemContext context;
    private Address address;

    public DBServiceHibernateImpl(Address address, MessageSystemContext messageSystemContext) {
        sessionFactory = createSessionFactory(HibernateConfiguration.setup());
        this.context = messageSystemContext;
        this.address = address;

    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public <T extends DataSet> void save(T user) {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO usersDAO = new UsersDAO(session);
            usersDAO.save(user);
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            T object = dao.load(clazz, id);
            return object;
        }
    }

    @Override
    public <T extends DataSet> String getUserById(long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            String userById = dao.getUserById(id, clazz);
            return userById;
        }
    }

    @Override
    public Integer getCountUsers() {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            Integer countUsers = dao.getCountUsers();
            return countUsers;
        }
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

    public void shutdown() {
        sessionFactory.close();
    }

    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setDbAddress(this.getAddress());
    }


    public List<UserDataSet> readAll() {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            return dao.readAll();
        }
    }
}
