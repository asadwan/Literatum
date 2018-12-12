package webadmintool;

import model.JournalDAO;
import model.Publication;
import model.PublicationDAO;
import model.User;
import utility.Utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateUserServlet", urlPatterns = "/wat/create-user")
public class CreateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String userType = request.getParameter("user-type");
        String[] licenses = request.getParameterValues("licenses");
        User user = new User(userType, username, Utility.sha160(password), name);
        CreateUserHandler createUserHandler = new CreateUserHandler(user, licenses, request.getSession());
        boolean isUserCreated = createUserHandler.create();
        String strBool = isUserCreated ? "true" : "false";
        request.getSession().setAttribute("userCreated", strBool);
        request.getRequestDispatcher("/wat/create-user.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicationDAO journalDAO = new JournalDAO();
        List<Publication> journals =  journalDAO.getAllPublications();
        request.setAttribute("journals", journals);
        request.getRequestDispatcher("/wat/create-user.jsp").forward(request, response);
    }
}
