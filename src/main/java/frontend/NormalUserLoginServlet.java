package frontend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lit/login")
public class NormalUserLoginServlet extends HttpServlet {


    private String username;
    private String password;
    private String token;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        try {
            HttpResponse<JsonNode> authResponse = requestAuthentication();
            if (authResponse.getStatus() == 200) {
                token = (String) authResponse.getBody().getObject().get("token");
                redirectToLitHomepage(request, response);
            } else {
                redirectToLitLoginPage(request, response);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/lit/login.jsp").forward(request, response);
    }

    private HttpResponse<JsonNode> requestAuthentication() throws UnirestException {
        HttpResponse<JsonNode> authResponse = Unirest.post("http://localhost:5000/auth/login")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("username", username)
                .field("password", password)
                .field("user-type", "normal")
                .asJson();
        return authResponse;
    }

    private void redirectToLitHomepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("token", token);
        request.getSession().setAttribute("username", username);
        response.sendRedirect("/lit");
    }

    private void redirectToLitLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("normalUserFailedLogin", true);
        response.sendRedirect("/lit/login");
    }
}
