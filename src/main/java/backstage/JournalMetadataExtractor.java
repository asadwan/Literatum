package backstage;

import model.Journal;
import utility.Utility;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.File;

public class JournalMetadataExtractor implements MetadataExtractor {

    private File journalXmlFile;

    public JournalMetadataExtractor(File journalXmlFile) {
        this.journalXmlFile = journalXmlFile;
    }

    public Journal extract() throws Exception {
        Document document = Utility.getDocument(journalXmlFile);
        Element rootElement = document.getRootElement();
        String issn = rootElement.getChild("journal-meta").getChild("issn").getValue();
        Journal journal = new Journal();
        journal.setId(issn);
        return journal;
    }
}
