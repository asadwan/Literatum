package backstage;

import javax.xml.XMLConstants;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class ArticleHtmlGenerator {

    private File outputHtmlDir;
    private File articleXml;

    public ArticleHtmlGenerator(File outputHtmlDir, File articleXml) {
        this.outputHtmlDir = outputHtmlDir;
        this.articleXml = articleXml;
    }

    public void generate() throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Source xslt = new StreamSource(new File("/home/aadwan/IdeaProjects/Lit/resources/article.xsl"));
        Transformer transformer = transformerFactory.newTransformer(xslt);
        Source xml = new StreamSource(articleXml);
        File output = new File(outputHtmlDir + "/article.html");
        output.getParentFile().mkdirs();
        transformer.transform(xml, new StreamResult(output));
    }
}
