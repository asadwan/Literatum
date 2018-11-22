package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static utility.Utility.getSessionFactory;


public class IssueDAO implements DAO {

    @Override
    public void create(Publication publication) throws Exception {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Issue issue = (Issue) publication;
            issue.generateIssueId();
            session.save(issue);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println();
            e.printStackTrace();
            throw new Exception("An error has occurred while persisting issue \"" + publication.getId() + "\"");
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

    @Override
    public List<Publication> getAllPublications() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Issue> query = criteriaBuilder.createQuery(Issue.class);
        Root<Issue> root = query.from(Issue.class);
        query.select(root);
        Query issueQuery = session.createQuery(query);
        List<Publication> issues = issueQuery.getResultList();
        session.close();
        return issues;
    }
}
