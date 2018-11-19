package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static utility.Utility.getSessionFactory;


public class IssueDAO implements DAO {

    @Override
    public void create(Publication publication) {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Issue issue = (Issue) publication;
            issue.generateIssueId();
            session.save(issue);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println("An error has occurred while persisting issue " + publication.getId());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Publication retrieve(String issueId) {
        Session session = getSessionFactory().openSession();
        Issue issue = session.get(Issue.class, issueId);
        session.close();
        return issue;
    }
}
