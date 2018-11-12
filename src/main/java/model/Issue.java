package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issue")
public class Issue {

    @Column(name = "issue_id")
    @Id

    private String issueId;

    @Column(name = "issue_title")
    private String issueTitle;

    @Column(name = "issue_pub_date")
    private String issuePubDate;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @OneToMany(mappedBy = "issue")
    private Set<Article> articles = new HashSet<>();

    public Issue(String issueId, String issueTitle, String issuePubDate, Journal journal) {
        this.issueId = issueId;
        this.issueTitle = issueTitle;
        this.issuePubDate = issuePubDate;
        this.journal = journal;
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

    public Issue() {}

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssuePubDate() {
        return issuePubDate;
    }

    public void setIssuePubDate(String issuePubDate) {
        this.issuePubDate = issuePubDate;
    }
}
