package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ArticleDAO {

    public static SessionFactory getSessionFactory() {

        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public static void createArticle(Article article) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error occurred while persisting article " + article.getArticleName());
        } finally {
            session.close();
        }
    }

}