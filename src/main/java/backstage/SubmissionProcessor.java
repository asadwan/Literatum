package backstage;

import model.Issue;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import java.io.File;

public class SubmissionProcessor {

    private static final String compressedPath = "/home/aadwan/IdeaProjects/Lit/resources/submissions/compressed/";
    private static final String uncompressedPath = "/home/aadwan/IdeaProjects/Lit/resources/submissions/uncompressed/";
    private String submission;

    public SubmissionProcessor(String submission) {
        this.submission = submission;
    }

    public void process() {
        unzip();
        IssueMetadataExtactor ime = new IssueMetadataExtactor("/home/aadwan/IdeaProjects/Lit/resources/submissions/uncompressed/" +
                "afpa_afpa_8_2_20181029030821216/afpa_8_2/issue-files/afpa_8_2.xml");
        ime.extract();
    }

    private void unzip() {
        String destinationFolder = submission.split("\\.")[0];
        File destinationDir = new File(uncompressedPath + destinationFolder);
        boolean success = destinationDir.mkdir();
        if (success) {
            try {
                ZipFile zipFile = new ZipFile(compressedPath + submission);
                zipFile.extractAll(uncompressedPath + destinationFolder);
            } catch (ZipException e) {
                e.printStackTrace();
            }

        }
    }

}
