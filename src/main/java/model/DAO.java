package model;
import java.util.List;

public interface DAO {

    void create(Publication publication) throws Exception;
    Publication retrieve(String publicationId);
    List<Publication> getAllPublications();
}
