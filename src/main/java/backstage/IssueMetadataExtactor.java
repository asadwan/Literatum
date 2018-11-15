package backstage;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.xml.parsers.SAXParser;
import java.io.*;


public class IssueMetadataExtactor {

    private String issueXmlFileName;

    public IssueMetadataExtactor(String issueXmlFileName) {
        this.issueXmlFileName = issueXmlFileName;
    }

    public void extract() {
        File issueXmlFile = new File(issueXmlFileName);
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setValidation(false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document document = null;
        try {
            document = saxBuilder.build(issueXmlFile);
        } catch (JDOMException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getChild("journal-meta").getChild("journal-title-group").getChild("journal-title").getValue());
    }
}
