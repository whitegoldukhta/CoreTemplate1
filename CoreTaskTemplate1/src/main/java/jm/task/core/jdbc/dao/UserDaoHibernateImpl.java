package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS USER " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, " +
                        "age INTEGER NOT NULL)").addEntity(User.class).executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();

                session.createSQLQuery("DROP TABLE IF exists jdbc_user.USER").executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();
                userList = session.createQuery("from User", User.class).getResultList();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    e.printStackTrace();
                }
            }
            return userList;
        }
    }
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = null;
//
//        transaction = session.beginTransaction();
//        List user = session.createQuery("FROM User").list();
//
//        transaction.commit();
//        session.close();
//        return (List<User>) user;

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            try {
                transaction = session.beginTransaction();
                session.createQuery("DELETE FROM User").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
