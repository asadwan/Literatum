package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issue")
public class Issue implements Publication {

    @Column(name = "issue_id")
    @Id
    private String issueId;

    @Column(name = "issue_number")
    private int issueNumber;

    @Column(name = "volume")
    private int volume;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "issue_pub_date")
    private Date issuePubDate;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    public Issue() {
    }

    public Issue(int issueNumber, int volume, String issueType, Date issuePubDate) {
        this.issueNumber = issueNumber;
        this.volume = volume;
        this.issueType = issueType;
        this.issuePubDate = issuePubDate;
    }

    public void generateIssueId() {
        this.issueId = journal.getId() + "_" + volume + "-" + issueNumber;
    }

    @Override
    public String getId() {
        return issueId;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public Date getIssuePubDate() {
        return issuePubDate;
    }

    public void setIssuePubDate(Date issuePubDate) {
        this.issuePubDate = issuePubDate;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
