package backstage;

public class Test {

    public static void main(String[] args) {
        IssueMetadataExtactor ime = new IssueMetadataExtactor("/home/aadwan/IdeaProjects/Lit/resources/submissions/uncompressed/" +
                "afpa_afpa_8_2_20181029030821216/afpa_8_2/issue-files/afpa_8_2.xml");
        ime.extract();
    }
}
