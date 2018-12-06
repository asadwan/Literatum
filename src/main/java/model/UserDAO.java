package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDAO {

    public static SessionFactory getSessionFactory() {

        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public static String createUser(User newUser) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.clear();
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return newUser.getUsername();
    }

    public static void deleteUser(User newUser) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(newUser);
        transaction.commit();
        session.close();
    }

    public static User getUser(String username) {
        Session session = getSessionFactory().openSession();
        User user = session.get(User.class, username);
        session.close();
        return user;
    }

    public static void updateUser(User user) throws Exception {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            session.close();
        }
    }
}
