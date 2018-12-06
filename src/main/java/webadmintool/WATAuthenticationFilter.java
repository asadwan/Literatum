package webadmintool;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/wat/*")
public class WATAuthenticationFilter implements Filter {

    private HttpSession session;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        servletRequest = (HttpServletRequest) req;
        servletResponse = (HttpServletResponse)resp;
        session = servletRequest.getSession();
        if(getResourceName().equalsIgnoreCase("logout")) {
            redirectToLoginPage();
            return;
        }
        if(getToken() == null && !getResourceName().equals("login")) {
            redirectTo401ErrorPage();
            return;
        }
        try {
            if(!getResourceName().equals("login") && !isTokenValid(getToken(), getUsername())) {
                redirectTo401ErrorPage();
                return;
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        chain.doFilter(req, resp);
    }

    private void redirectToLoginPage() throws IOException {
        session.removeAttribute("token");
        servletResponse.sendRedirect("/wat/login");
    }

    private void redirectTo401ErrorPage() throws ServletException, IOException {
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        servletRequest.getRequestDispatcher("/error-pages/401.jsp")
                .forward(servletRequest, servletResponse);;
    }

    private String getUsername() {
        return (String)session.getAttribute("username");
    }

    private boolean isTokenValid(String token, String username) throws UnirestException {
        JSONObject requestJson = new JSONObject();
        requestJson.put("token", token);
        requestJson.put("username", username);
        HttpResponse<String> isTokenValidResponse = Unirest.get("http://localhost:5000/auth/validate")
                .header("Content-Type", "application/json")
                .queryString("token", token)
                .queryString("username", username)
                .queryString("access-domain", "wat")
                .asString();
        return isTokenValidResponse.getStatus() == 200;
    }

    private String getToken() {
        return (String)session.getAttribute("token");
    }

    private String getResourceName() {
        String path = servletRequest.getServletPath();
        String[] pathParts = path.split("/");
        return Arrays.stream(pathParts).reduce((first, second) -> second).orElse("login");
    }
}
