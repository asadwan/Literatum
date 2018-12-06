package frontend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "accessControlFilter" )
public class AccessControlFilter implements Filter {

    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private HttpSession session;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        servletRequest = (HttpServletRequest) req;
        servletResponse = (HttpServletResponse) resp;
        session = servletRequest.getSession();
        if(isRequestedResourceNotPublication() || isRequestedResourceMediaFile()) {
            chain.doFilter(req, resp);
            return;
        }
        String publicationId = getPublicationId();
        String publicationType = getPublicationType();
        boolean accessGranted = false;
        if("journal".equalsIgnoreCase(publicationType)) {
            accessGranted = isAccessGranted(publicationId);
        } else if (("issue".equalsIgnoreCase(publicationType) || "article".equalsIgnoreCase(publicationType))) {
            accessGranted = isAccessGranted(getJournalId(publicationId, publicationType));
        }
        if(!accessGranted) {
            redirectToAccessDeniedErrorPage();
            return;
        }
        chain.doFilter(req,resp);
    }

    private boolean isRequestedResourceNotPublication() {
        String[] servletPathParts = servletRequest.getServletPath().split("/");
        String reqResource = Arrays.stream(servletPathParts).reduce((first, second) -> second).orElse("login");
        return !"journal".equalsIgnoreCase(reqResource) && !"issue".equalsIgnoreCase(reqResource)
                && !"article".equalsIgnoreCase(reqResource);
    }

    private boolean isRequestedResourceMediaFile() {
        String[] pathParts = servletRequest.getPathInfo().split("/");
        String resourceId = Arrays.stream(pathParts).reduce((first, second) -> second).orElse("");
        return resourceId.endsWith(".gif") || resourceId.endsWith("pdf")
                ||resourceId.endsWith(".png") || resourceId.endsWith(".css")
                || resourceId.endsWith(".tif");
    }

    private String getPublicationType() {
        String path = servletRequest.getServletPath();
        String[] pathParts = path.split("/");
        String pubType = 2 >= pathParts.length ? "" : pathParts[2] ;
        return pubType;
    }

    private String getPublicationId() {
        String path = servletRequest.getPathInfo()  ;
        String[] pathParts = path.split("/");
        return Arrays.stream(pathParts).reduce((first, second) -> second).orElse("");
    }

    private String getJournalId(String publicationId, String publicationType) {
        if("issue".equalsIgnoreCase(publicationType)) {
            return getJournalOfIssue(publicationId).getId();
        } else if ("article".equalsIgnoreCase(publicationType)) {
            return getJournalOfArticle(publicationId).getId();
        }
        return "";
    }

    private boolean isAccessGranted(String journalId) {
        String username = (String) session.getAttribute("username");
        HttpResponse<String> accessResponse = null;
        try {
            accessResponse = Unirest.get("http://localhost:5000/access-control/journal/" + journalId)
                    .queryString("username",  username).asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return accessResponse.getStatus() == 200;
    }

    private void redirectToAccessDeniedErrorPage() throws ServletException, IOException {
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        servletRequest.getRequestDispatcher("/error-pages/access-denied.jsp")
                .forward(servletRequest, servletResponse);
    }

    private Journal getJournalOfIssue(String issueId) {
        PublicationDAO issueDAO = new IssueDAO();
        Issue issue = (Issue) issueDAO.retrieve(issueId);
        return issue.getJournal();
    }

    private Journal getJournalOfArticle(String articleId) {
        PublicationDAO articleDAO = new ArticleDAO();
        Article article = (Article) articleDAO.retrieve(articleId);
        if(article == null) return null;
        return article.getIssue().getJournal();
    }
}
