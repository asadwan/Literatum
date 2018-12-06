package model;
import java.util.List;

public interface PublicationDAO {

    void create(Publication publication) throws Exception;
    Publication retrieve(String publicationId);
    List<Publication> getAllPublications();
}
