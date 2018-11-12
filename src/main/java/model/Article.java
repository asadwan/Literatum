package model;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Column(name = "article_id")
    @Id
    private String articleId;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "article_category")
    private String articleCategory;

    @ManyToOne
    @JoinColumn
    private Issue issue;

    public Article(String articleId, String articleName, String issueId, String articleCategory, Issue issue) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleCategory = articleCategory;
        this.issue = issue;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Article() {
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }
}

