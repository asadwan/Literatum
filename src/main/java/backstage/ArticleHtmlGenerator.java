package backstage;

import net.sf.saxon.TransformerFactoryImpl;
import org.jdom2.transform.JDOMSource;
import utility.Utility;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;


public class ArticleHtmlGenerator {

    private static final String STYLESHEET = "/home/aadwan/IdeaProjects/Lit/resources/XSLT/jats-to-html.xsl";
    private File outputHtmlDir;
    private File articleXml;

    public ArticleHtmlGenerator(File outputHtmlDir, File articleXml) {
        this.outputHtmlDir = outputHtmlDir;
        this.articleXml = articleXml;
    }

    public void generate() throws Exception {
        TransformerFactory transformerFactory = TransformerFactoryImpl.newInstance();
        Source xslt = new StreamSource(new File(STYLESHEET));
        Transformer transformer = transformerFactory.newTransformer(xslt);
        Source xml = new JDOMSource(Utility.getDocument(articleXml));
        File output = new File(outputHtmlDir + "/article.html");
        output.getParentFile().mkdirs();
        transformer.transform(xml, new StreamResult(output));
    }
}
