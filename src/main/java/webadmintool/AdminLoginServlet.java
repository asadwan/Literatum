package webadmintool;

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

@WebServlet("webadmintool/login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adminLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        try {
            HttpResponse<JsonNode> authResponse = Unirest.post("http://localhost:5000/auth/login")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("username", username)
                    .field("password", password)
                    .asJson();
            System.out.println(authResponse.getBody().getObject().get("message"));
            if(authResponse.getStatus() == 200) {
               resp.sendRedirect("/upload");
            } else {
                resp.sendRedirect("/login");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
