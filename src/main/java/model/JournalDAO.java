package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static utility.Utility.getSessionFactory;

public class JournalDAO implements DAO {

    @Override
    public void create(Publication publication) {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Journal journal = (Journal) publication;
            session.save(journal);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println("an error has occurred while persisting journal " + publication.getTitle());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Publication retrieve(String journalId) {
        Session session = getSessionFactory().openSession();
        Journal journal = session.get(Journal.class, journalId);
        session.close();
        return journal;
    }
}
