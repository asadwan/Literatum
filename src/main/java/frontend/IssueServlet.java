package frontend;

import model.Issue;
import model.IssueDAO;
import model.PublicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lit/issue/*")
public class IssueServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String issueId = request.getPathInfo().split("/")[1];
        PublicationDAO issueDAO = new IssueDAO();
        Issue issue;
        if((issue = (Issue) issueDAO.retrieve(issueId)) == null) {
            response.setStatus(404);
            return;
        }
        request.setAttribute("issue", issue);
        request.setAttribute("journal", issue.getJournal());
        request.setAttribute("articles", issue.getArticles());
        request.getRequestDispatcher("/lit/issue.jsp").forward(request,response);
    }
}
