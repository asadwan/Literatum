package utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.io.IOException;

public final class Utility {

    public static Document getDocument(File xmlFile) throws Exception {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setValidation(false);
//        saxBuilder.setFeature("http://xml.org/sax/features/namespaces", false);
//        saxBuilder.setFeature("http://xml.org/sax/features/validation", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document document = null;
        try {
            document = saxBuilder.build(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return document;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        return configuration.configure().buildSessionFactory();
    }
}
