package frontend;

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

@WebServlet("/lit/")
public class JournalsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDAO journalDAO = new JournalDAO();
        List<Publication> journals =  journalDAO.getAllPublications();
        request.setAttribute("journals", journals);
        request.getRequestDispatcher("/lit/index.jsp").forward(request, response);
    }
}
