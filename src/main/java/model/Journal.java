package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "journal")
public class Journal {

    @Column(length = 100, nullable = false, name = "journal_id")
    @Id
    private String journalId;

    @Column(length = 100, nullable = false, name = "journal_name")
    private String journalName;

    @Column(length = 100, nullable = false, name = "publisher_name")
    String publisherName;


    @OneToMany(mappedBy = "journal")
    private Set<Issue> issues = new HashSet<>();

    public Journal(String journalId, String journalName, String publisherName) {
        this.journalId = journalId;
        this.journalName = journalName;
        this.publisherName = publisherName;
    }

    public Journal() {
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
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
