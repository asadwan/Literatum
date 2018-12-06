package backstage;

import com.google.common.io.Files;
import model.*;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import utility.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class SubmissionProcessor {

    private static final String COMPRESSED_PATH = "/home/aadwan/IdeaProjects/Lit/resources/submissions/compressed/";
    private static final String UNCOMPRESSED_PATH = "/home/aadwan/IdeaProjects/Lit/resources/submissions/uncompressed/";
    private static final String JOURNALS_DIR_PATH = "/home/aadwan/IdeaProjects/Lit/resources/content/journals/";
    private String submission;
    private Journal journal;
    private Issue issue;
    private Article article;
    private String submissionDirPath;
    private String issueDirPath;

    public SubmissionProcessor(String submission) {
        this.submission = submission;
    }

    public boolean process() {
        unzip();
        File issueXmlFile = locateIssueXmlFile();
        File articleXmlFile = locateArticleXmlFile();
        try {
            if (!isJournalPresent(issueXmlFile)) {
                System.out.println("The journal this article belongs to does not exist, please create it" +
                        " using the Web Admin Tool and try again.");
                return false;
            }
            processIssueData(issueXmlFile);
            processArticleData(articleXmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void unzip() {
        String destinationFolder = submission.split("\\.")[0];
        submissionDirPath = UNCOMPRESSED_PATH + destinationFolder;
        File destinationDir = new File(submissionDirPath);
        boolean success = destinationDir.mkdir();
        System.out.println(success);
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
        File submissionDir = new File(submissionDirPath);
        Optional<File> subDir = Arrays.stream(submissionDir.listFiles(File::isDirectory)).findFirst();
        Optional<File> issueFilesDir = Arrays.stream(subDir.get().listFiles(File::isDirectory))
                .filter(file -> file.getName().equalsIgnoreCase("issue-files")).findFirst();
        Optional<File> issueXmlFile = Arrays.stream(issueFilesDir.get().
                listFiles(file -> file.getName().endsWith("xml"))).findFirst();
        return issueXmlFile.get();
    }

    private File locateArticleXmlFile() {
        File submissionDir = new File(submissionDirPath);
        Optional<File> subDir = Arrays.stream(submissionDir.listFiles(File::isDirectory)).findFirst();
        Optional<File> articleFilesDir = Arrays.stream(subDir.get().listFiles(File::isDirectory))
                .filter(file -> !file.getName().equalsIgnoreCase("issue-files")).findFirst();
        Optional<File> articleXmlFile = Arrays.stream(articleFilesDir.get().
                listFiles(file -> file.getName().endsWith("xml"))).findFirst();
        return articleXmlFile.get();
    }

    private boolean isJournalPresent(File issueXmlFile) throws Exception {
        MetadataExtractor jmde = new JournalMetadataExtractor(issueXmlFile);
        journal = (Journal)jmde.extract();
        PublicationDAO journalDAO = new JournalDAO();
        if (journalDAO.retrieve(journal.getId()) == null) return false;
        return true;
    }

    private void processIssueData(File issueXmlFile) throws Exception {
        MetadataExtractor imde = new IssueMetadataExtractor(issueXmlFile);
        issue = (Issue)imde.extract();
        String issueId = journal.getId() + "_" + issue.getVolume() + "-" + issue.getIssueNumber();
        PublicationDAO issueDAO = new IssueDAO();
        issueDirPath = JOURNALS_DIR_PATH+journal.getId()+"/"+issueId;
        if (issueDAO.retrieve(issueId) != null) {
            issue = (Issue)issueDAO.retrieve(issueId);
            return;
        }
        issue.setJournal(journal);
        issueDAO.create(issue);
        File issueDir = new File(issueDirPath);
        issueDir.mkdir();
        System.out.println("Issue " + issue.getId() + " metadata saved to database");
    }

    private void processArticleData(File articleXmlFile) throws Exception {
        MetadataExtractor amde = new ArticleMetadataExtractor(articleXmlFile);
        article = (Article)amde.extract();
        PublicationDAO articleDAO = new ArticleDAO();
        if (articleDAO.retrieve(article.getArticleId()) == null) {
            article.setIssue(issue);
            articleDAO.create(article);
            String articleDirPath = issueDirPath + "/" + article.getId();
            File articleDir = new File(articleDirPath);
            ArticleHtmlGenerator articleHtmlGenerator = new ArticleHtmlGenerator(articleDir, articleXmlFile);
            articleHtmlGenerator.generate();
            copyMediaFilesToArticleDir(articleDir);
        }
    }

    private void copyMediaFilesToArticleDir(File articleDir) throws IOException {
        File submissionDir = new File(submissionDirPath);
        Set<File> mediaFiles = Utility.getAllMediaFiles(submissionDir);
        for (File mediaFile: mediaFiles) {
            File dest = new File(articleDir + "/" + mediaFile.getName());
            dest.createNewFile();
            Files.copy(mediaFile, dest);
        }
    }
}
