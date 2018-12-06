package frontend;

import model.Article;
import model.ArticleDAO;
import model.PublicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

@WebServlet("/lit/article/*")
public class ArticleServlet extends HttpServlet {

    private static final String JOURNALS_DIR_PATH = "/home/aadwan/IdeaProjects/Lit/resources/content/journals/";
    private static final String ARTICLE_CSS_FILE_PATH = "/home/aadwan/IdeaProjects/Lit/resources/CSS/jats-preview.css";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String[] pathParts = path.split("/");
        String resource = pathParts[1];
        Article article;
        if ((article = getArticle(resource)) != null) {
            returnArticleHtmlFile(response, article);
            return;
        } else if (resource.endsWith(".css")) {
            returnArticleCssFile(response);
            return;
        } else if (resource.endsWith(".pdf")) {
            returnArticlePdfFile(response, request, resource);
        } else if(resource.endsWith(".tif")) {
                returnTifImage(response, request, resource);
        } else {
            response.setStatus(404);
            return;
        }
    }

    private void returnArticleHtmlFile(HttpServletResponse response, Article article) throws IOException {
        String journalId = article.getIssue().getJournal().getId();
        String issueId = article.getIssue().getId();
        String articleHtmlFilePath = JOURNALS_DIR_PATH + journalId + "/" + issueId + "/" + article.getId() + "/"
                + "article.html";
        writeFileToResponse(response, articleHtmlFilePath, "text/html");
    }

    private void returnArticleCssFile(HttpServletResponse response) throws IOException {
        writeFileToResponse(response, ARTICLE_CSS_FILE_PATH, "text/css");
    }

    private void returnArticlePdfFile(HttpServletResponse response, HttpServletRequest request, String articlePdfFileName) throws IOException {
        String articlePdfFilePath;
        if(getPdfFilePathFromSession(articlePdfFileName, request) != null) {
            articlePdfFilePath = getPdfFilePathFromSession(articlePdfFileName,request);
        } else if (getArticleIdFromRefererURI(request) != null) {
            Article article = (Article) new ArticleDAO().retrieve(getArticleIdFromRefererURI(request));
            articlePdfFilePath = JOURNALS_DIR_PATH + article.getIssue().getJournal().getId() +
                    "/" + article.getIssue().getId() + "/" + article.getId() + "/" + articlePdfFileName;
            request.getSession().setAttribute(articlePdfFileName, articlePdfFilePath);
        } else {
            response.setStatus(404);
            return;
        }
        writeFileToResponse(response, articlePdfFilePath, "application/pdf");
    }

    private void returnTifImage(HttpServletResponse response, HttpServletRequest request, String tifName) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return;
        String articleId = cookies[1].getValue();
        String issueId = cookies[2].getValue();
        String journalId = cookies[0].getValue();
        String tifPath = JOURNALS_DIR_PATH + journalId + "/" + issueId + "/" + articleId + "/"
                + tifName;
        writeFileToResponse(response, tifPath, "image/tiff");
    }

    private void writeFileToResponse(HttpServletResponse response, String filePath, String mediaType) throws IOException {
        OutputStream out = response.getOutputStream();
        response.setContentType(mediaType);
        File file = new File(filePath);
        Files.copy(file.toPath(), out);
    }

    private Article getArticle(String articleId) {
        Article article = (Article) new ArticleDAO().retrieve(articleId);
        return article;
    }

    private String getArticleIdFromRefererURI(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (referer == null) return null;
        return Arrays.stream(referer.split("/")).reduce((first, second) -> second).get();
    }

    private String getPdfFilePathFromSession(String articlePdfFileName, HttpServletRequest request) {
        Object path = request.getSession().getAttribute(articlePdfFileName);
        if(path == null) return null;
        return (String)path;
    }
}
