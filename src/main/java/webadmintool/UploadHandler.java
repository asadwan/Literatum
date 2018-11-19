package webadmintool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

public class UploadHandler {

    public static final String compressedPath =  "/home/aadwan/IdeaProjects/Lit/resources/submissions/compressed/";

    public void upload(HttpServletRequest request) throws IOException, ServletException {

        Part filePart = request.getPart("pub");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileInputStream = filePart.getInputStream();
        saveFile(fileName, fileInputStream);
        request.getSession().setAttribute("newSubmission", fileName);
    }

    private void saveFile(String fileName, InputStream fileInputStream) throws IOException {
        File file = new File(compressedPath+fileName);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len = fileInputStream.read(buffer);
        while (len != -1) {
            outputStream.write(buffer, 0, len);
            len = fileInputStream.read(buffer);
        }
        fileInputStream.close();
        outputStream.close();
    }
}
