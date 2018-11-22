package model;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article implements Publication {

    @Column(name = "article_id")
    @Id
    private String articleId;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_category")
    private String articleCategory;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public Article(String articleId, String articleTitle, String articleCategory) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleCategory = articleCategory;
    }

    public Article() {}

    @Override
    public String getId() {
        return this.getArticleId();
    }

    @Override
    public String getTitle() {
        return this.articleTitle;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleName) {
        this.articleTitle = articleName;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }
}

