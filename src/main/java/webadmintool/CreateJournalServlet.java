package webadmintool;


import model.Journal;
import model.JournalDAO;
import model.Publication;
import model.PublicationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/wat/create-journal")
public class CreateJournalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String issn = req.getParameter("issn");
        String title = req.getParameter("title");
        String publisher = req.getParameter("publisher");
        String description = req.getParameter("desc");
        Journal journal = new Journal(issn, title, publisher, description);
        System.out.println(description);
        CreateJournalhandler createJournalhandler = new CreateJournalhandler(journal, req.getSession());
        boolean success = createJournalhandler.create();
        String message = success ? "true" : "false";
        req.setAttribute("creationSuccessful", message);
        req.getRequestDispatcher("/wat/create-journal.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/wat/create-journal.jsp").forward(req, resp);
    }
}
