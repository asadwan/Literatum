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

public class ArticleDAO implements DAO {

    @Override
    public void create(Publication publication) throws Exception {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Article article = (Article) publication;
            session.save(article);
            transaction.commit();
            session.close();
            System.out.println("Article \"" + article.getArticleTitle() + "\" metadata has been persisted to database.");
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new Exception("An error has occurred while persisting article \"" + publication.getTitle() + "\"");
        } finally {
            session.close();
        }
    }

    @Override
    public Publication retrieve(String publicationId) {
        Session session = getSessionFactory().openSession();
        Article article = session.get(Article.class, publicationId);
        session.close();
        return article;
    }

    @Override
    public List<Publication> getAllPublications() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);
        query.select(root);
        Query articleQuery = session.createQuery(query);
        List<Publication> articles = articleQuery.getResultList();
        session.close();
        return articles;
    }
}