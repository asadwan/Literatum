package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "journal")
public class Journal implements Publication {

    @Column(length = 100, nullable = false, name = "journal_id")
    @Id
    private String journalId;

    @Column(length = 100, nullable = false, name = "journal_title")
    private String journalTitle;

    @Column(length = 100, nullable = false, name = "publisher_name")
    String publisherName;


    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Issue> issues = new HashSet<>();

    public Journal(String journalId, String journalTitle, String publisherName) {
        this.journalId = journalId;
        this.journalTitle = journalTitle;
        this.publisherName = publisherName;
    }

    public Journal() {}

    @Override
    public String getId() {
        return journalId;
    }

    public void setId(String journalId) {
        this.journalId = journalId;
    }

    @Override
    public String getTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalName) {
        this.journalTitle = journalName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
