package webadmintool;

import model.*;
import utility.Utility;

import javax.servlet.http.HttpSession;
import java.io.File;

public class CreateJournalhandler {

    private static final String JOURNALS_DIR_PATH = "/home/aadwan/IdeaProjects/Lit/resources/content/journals/";
    private Journal journal;
    private HttpSession session;

    public CreateJournalhandler(Journal journal, HttpSession session) {
        this.journal = journal;
        this.session = session;
    }

    public boolean create() {
        if(doesJournalExist()) {
            session.setAttribute("journalCreationMessage", "Journal already exists");
            return false;
        }
        License license;
        if((license =createJournalLicense()) == null) {
            session.setAttribute("journalCreationMessage", "Could not create journal. please try again");
            return false;
        }
        journal.setLicense(license);
        if(presistNewJournal() == true) {
            session.setAttribute("journalCreationMessage", "Journal has been created successfully");
            return true;
        }
        session.setAttribute("journalCreationMessage", "Could not create journal. please try again");
        return  false;
    }

    private boolean doesJournalExist() {
        PublicationDAO journalDAO = new JournalDAO();
        return journalDAO.retrieve(journal.getId()) != null;
    }

    private boolean presistNewJournal() {
        PublicationDAO journalDAO = new JournalDAO();
        try {
            journalDAO.create(journal);
            File journalDir = new File(JOURNALS_DIR_PATH+journal.getId());
            journalDir.mkdir();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private License createJournalLicense() {
        License license = new License(Utility.md5(journal.getTitle()),
                "Access to journal \"" + journal.getTitle() + "\"");
        return LicenseDAO.create(license);
    }
 }
