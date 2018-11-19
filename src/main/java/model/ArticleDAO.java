package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static utility.Utility.getSessionFactory;

public class ArticleDAO implements DAO {

    @Override
    public void create(Publication publication) {

        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Article article = (Article) publication;
            session.save(article);
            transaction.commit();
            session.close();
            System.out.println("Article \"" + article.getArticlTitle() + "\" metadata has been persisted to database.");
        } catch (HibernateException e) {
            System.out.println("An error has occurred while persisting article \"" + publication.getTitle() + "\"");
            e.printStackTrace();
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

}