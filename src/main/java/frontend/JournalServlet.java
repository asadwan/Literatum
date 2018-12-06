package frontend;

import model.Journal;
import model.JournalDAO;
import model.PublicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lit/journal/*")
public class JournalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String[] pathParts = path.split("/");
        String journalId = pathParts[1];
        PublicationDAO journalDAO = new JournalDAO();
        Journal journal = (Journal) journalDAO.retrieve(journalId);
        if(journal == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("journal", journal);
        req.setAttribute("issues", journal.getIssues());
        req.getRequestDispatcher("/lit/journal.jsp").forward(req,resp);
    }
}
