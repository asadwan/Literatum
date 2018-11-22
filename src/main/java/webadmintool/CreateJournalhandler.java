package webadmintool;

import model.DAO;
import model.Journal;
import model.JournalDAO;

import java.io.File;

public class CreateJournalhandler {

    private static final String JOURNALS_DIR_PATH = "/home/aadwan/IdeaProjects/Lit/resources/content/journals/";

    public boolean create(String issn, String title, String publisher) {
        DAO journalDAO = new JournalDAO();
        Journal journal = new Journal(issn, title, publisher);
        try {
            journalDAO.create(journal);
            File journalDir = new File(JOURNALS_DIR_PATH+issn);
            journalDir.mkdir();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
