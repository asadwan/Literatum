package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class JournalDAO {

    public static SessionFactory getSessionFactory() {

        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public static void createJournal(Journal journal) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(journal);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while persisting journal " + journal.getJournalName());
        } finally {
            session.close();
        }
    }

    public static Journal getJournal(String journalId) {
        Session session = getSessionFactory().openSession();
        Journal journal = session.load(Journal.class, journalId);
        return journal;
    }
}
