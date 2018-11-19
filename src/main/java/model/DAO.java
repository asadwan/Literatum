package model;

public interface DAO {

    void create(Publication publication);
    Publication retrieve(String publicationId);
}
