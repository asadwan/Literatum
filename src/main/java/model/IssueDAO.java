package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class IssueDAO {

    public static SessionFactory getSessionFactory() {

        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public static void createIssue(Issue issue) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(issue);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error occurred while persisting issue " + issue.getIssueId());
        } finally {
            session.close();
        }
    }


}
