package webadmintool;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/wat/upload")
@MultipartConfig
public class UploadPublicationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!isAuthorized(request, response)) {
            return;
        }
        UploadHandler uploadHandler = new UploadHandler();
        uploadHandler.upload(request);
        request.getRequestDispatcher("/wat/upload.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAuthorized(request, response)) {
            return;
        }
        request.getRequestDispatcher("/wat/upload.jsp").forward(request, response);

    }


    private boolean isAuthorized(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/auth/validate")
                    .header("Authorization", "Bearer 3.141592653589793")
                    .asJson();
            if (response.getStatus() != 200) {
                res.sendError(401, response.getBody().toString());
                return false;
            }

        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
