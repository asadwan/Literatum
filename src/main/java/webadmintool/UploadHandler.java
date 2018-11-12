package webadmintool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;

public class UploadHandler {

    public void upload(HttpServletRequest request) throws IOException, ServletException {

        Part filePart = request.getPart("pub");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileInputStream = filePart.getInputStream();
        saveFile(fileName, fileInputStream);

    }

    private void saveFile(String fileName, InputStream fileInputStream) throws IOException {

        String submissionsPath = "/home/aadwan/IdeaProjects/Lit/resources/submissions/";
        File file = new File(submissionsPath+fileName);
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len = fileInputStream.read(buffer);
        while (len != -1) {
            outputStream.write(buffer, 0, len);
            len = fileInputStream.read(buffer);
        }
        //IOUtils.copy(fileInputStream, outputStream);
        fileInputStream.close();
        outputStream.close();
    }
}
