package webadmintool;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/wat/login")
public class AdminLoginServlet extends HttpServlet {

    private String token;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/wat/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            HttpResponse<JsonNode> authResponse = requestAdminAuthentication(username, password);
            if(authResponse.getStatus() == 200) {
                token = (String) authResponse.getBody().getObject().get("token");
                redirectToWATHomepage(req, resp, username);
            } else {
                redirectToWATLoginPage(req, resp);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void redirectToWATHomepage(HttpServletRequest req, HttpServletResponse resp, String username) throws IOException {
        req.getSession().setAttribute("token", token);
        req.getSession().setAttribute("username", username);
        resp.sendRedirect("/wat");
    }

    private HttpResponse<JsonNode> requestAdminAuthentication(String username, String password) throws UnirestException {
        return Unirest.post("http://localhost:5000/auth/login")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .field("username", username)
                        .field("password", password)
                        .field("user-type", "admin")
                        .field("requestedAccess", "wat")
                        .asJson();
    }

    private void redirectToWATLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("adminUserFailedLogin", true);
        response.sendRedirect("/wat/login");
    }
}

