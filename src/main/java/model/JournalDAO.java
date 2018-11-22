package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static utility.Utility.getSessionFactory;

public class JournalDAO implements DAO {

    @Override
    public void create(Publication publication) throws Exception {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Journal journal = (Journal) publication;
            session.save(journal);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("An error has occurred while persisting journal \"" + publication.getTitle() + "\"");
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

    @Override
    public List<Publication> getAllPublications() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Journal> query = criteriaBuilder.createQuery(Journal.class);
        Root<Journal> root = query.from(Journal.class);
        query.select(root);
        Query journalQuery = session.createQuery(query);
        List<Publication> journals = journalQuery.getResultList();
        session.close();
        return journals;
    }
}
