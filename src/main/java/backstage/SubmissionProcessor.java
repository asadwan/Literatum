package backstage;

import model.*;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class SubmissionProcessor {

    private static final String COMPRESSED_PATH = "/home/aadwan/IdeaProjects/Lit/resources/submissions/compressed/";
    private static final String UNCOMPRESSED_PATH = "/home/aadwan/IdeaProjects/Lit/resources/submissions/uncompressed/";
    private String submission;

    private Journal journal;
    private Issue issue;
    private Article article;

    public SubmissionProcessor(String submission) {
        this.submission = submission;
    }

    public void process() {
        unzip();
        File issueXmlFile = locateIssueXmlFile();
        File articleXmlFile = locateArticleXmlFile();
        if (!isJournalPresent(issueXmlFile)) {
            System.out.println("The journal this article belongs to does not exist, please create it" +
                " using the Web Admin Tool and try again.");
            return;
        }
        processIssueData(issueXmlFile);
        processArticleData(articleXmlFile);
    }

    private void unzip() {
        String destinationFolder = submission.split("\\.")[0];
        File destinationDir = new File(UNCOMPRESSED_PATH + destinationFolder);
        boolean success = destinationDir.mkdir();
        if (success) {
            try {
                ZipFile zipFile = new ZipFile(COMPRESSED_PATH + submission);
                zipFile.extractAll(UNCOMPRESSED_PATH + destinationFolder);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
    }

    private File locateIssueXmlFile() {
        String submissionPath = UNCOMPRESSED_PATH + submission.split("\\.")[0];
        File submissionDir = new File(submissionPath);
        Optional<File> subDir = Arrays.stream(submissionDir.listFiles(File::isDirectory)).findFirst();
        Optional<File> issueFilesDir = Arrays.stream(subDir.get().listFiles(File::isDirectory))
                .filter(file -> file.getName().equalsIgnoreCase("issue-files")).findFirst();
        Optional<File> issueXmlFile = Arrays.stream(issueFilesDir.get().
                listFiles(file -> file.getName().endsWith("xml"))).findFirst();
        return issueXmlFile.get();
    }

    private File locateArticleXmlFile() {
        String submissionPath = UNCOMPRESSED_PATH + submission.split("\\.")[0];
        File submissionDir = new File(submissionPath);
        Optional<File> subDir = Arrays.stream(submissionDir.listFiles(File::isDirectory)).findFirst();
        Optional<File> articleFilesDir = Arrays.stream(subDir.get().listFiles(File::isDirectory))
                .filter(file -> !file.getName().equalsIgnoreCase("issue-files")).findFirst();
        Optional<File> articleXmlFile = Arrays.stream(articleFilesDir.get().
                listFiles(file -> file.getName().endsWith("xml"))).findFirst();
        return articleXmlFile.get();
    }

    private boolean isJournalPresent(File issueXmlFile) {
        MetadataExtractor jmde = new JournalMetadataExtractor(issueXmlFile);
        journal = (Journal)jmde.extract();
        DAO journalDAO = new JournalDAO();
        if (journalDAO.retrieve(journal.getId()) == null) return false;
        return true;
    }

    private void processIssueData(File issueXmlFile) {
        MetadataExtractor imde = new IssueMetadataExtractor(issueXmlFile);
        issue = (Issue)imde.extract();
        String issueId = journal.getId() + "_" + issue.getVolume() + "-" + issue.getIssueNumber();
        DAO issueDAO = new IssueDAO();
        if (issueDAO.retrieve(issueId) != null) {
            issue = (Issue)issueDAO.retrieve(issueId);
            return;
        }
        issue.setJournal(journal);
        issueDAO.create(issue);
        System.out.println("Issue " + issue.getId() + " metadata saved to database");
    }

    private void processArticleData(File articleXmlFile) {
        MetadataExtractor amde = new ArticleMetadataExtractor(articleXmlFile);
        article = (Article)amde.extract();
        DAO articleDAO = new ArticleDAO();
        if (articleDAO.retrieve(article.getArticleId()) == null) {
            article.setIssue(issue);
            articleDAO.create(article);
        }
    }
}
