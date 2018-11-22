package backstage;

import utility.Utility;
import model.Article;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.File;

public class ArticleMetadataExtractor implements MetadataExtractor {

    private File articleXmlFile;

    public ArticleMetadataExtractor(File articleXmlFile) throws Exception {
        this.articleXmlFile = articleXmlFile;
    }

    @Override
    public Article extract() throws Exception {
        Document document = Utility.getDocument(articleXmlFile);
        Element root = document.getRootElement();
        Element articleMeta = root.getChild("front").getChild("article-meta");
        String articleId = articleMeta.getChildText("article-id").replaceAll("/", "_")
                .replaceAll("\\.", "");
        String articleCategory = articleMeta.getChild("article-categories").getChild("subj-group").getChildText("subject");
        String articleTitle = articleMeta.getChild("title-group").getChildText("article-title");
        Article article = new Article(articleId,articleTitle, articleCategory);
        return article;
    }
}
