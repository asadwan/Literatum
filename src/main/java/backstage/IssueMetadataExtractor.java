package backstage;
import model.Issue;
import utility.Utility;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class IssueMetadataExtractor implements MetadataExtractor {

    private File issueXmlFile;

    public IssueMetadataExtractor(File issueXmlFile) {
        this.issueXmlFile = issueXmlFile;
    }

    public Issue extract() throws Exception {
        Document document = Utility.getDocument(issueXmlFile);
        Element rootElement = document.getRootElement();
        String issueType = rootElement.getChild("issue-meta").getAttributeValue("issue-type");
        String yearOfPub = rootElement.getChild("issue-meta").getChild("pub-date").getChild("year").getValue();
        String monthOfPub = rootElement.getChild("issue-meta").getChild("pub-date").getChild("month").getValue();
        String volume = rootElement.getChild("issue-meta").getChild("volume").getValue();
        String issueNumber = rootElement.getChild("issue-meta").getChild("issue").getValue();
        String issueTitle = rootElement.getChild("issue-meta").getChildText("issue-title");
        Issue issue = new Issue(issueTitle, Integer.valueOf(issueNumber), Integer.valueOf(volume),
                issueType, getIssueDate(yearOfPub, monthOfPub));
        return issue;
    }

    private Date getIssueDate(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, Integer.valueOf(month));
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        Date date = calendar.getTime();
        return date;
    }
}
