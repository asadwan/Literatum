package utility;

import net.sf.saxon.trans.SymbolicName;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public final class Utility {

    public static String sha160(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static Document getDocument(File xmlFile) throws Exception {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setValidation(false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document document = null;
        try {
            document = saxBuilder.build(xmlFile);
            removeDoctypeFromXml(document, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
        return document;
    }

    public static String md5(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    private static void removeDoctypeFromXml(Document xmlDoc, File xmlFile) throws IOException {
        xmlDoc.setDocType(null);
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(xmlDoc, new FileOutputStream(xmlFile));
    }

    private static SessionFactory sessionFactory;
    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Set<File> getAllMediaFiles(File direcotry) {
        File[] allContentList = direcotry.listFiles();
        Set<File> files = new HashSet<>();
        if(allContentList != null) {
            for(File file: allContentList) {
                String fileName = file.getName();
                if(file.isFile() && (fileName.endsWith(".tif") || fileName.endsWith(".png") || fileName.endsWith(".pdf"))) {
                    files.add(file);
                } else if(file.isDirectory()) {
                    files.addAll(getAllMediaFiles(file));
                }
            }
        }
        return files;
    }
}
