package entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private long commentId;
    
    @Column(name = "comment_text", length = 1010, nullable = false)
    private String commentText;
    
    @Column(name = "comment_date", nullable = false)
    private Date commentDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "ON DELETE SET NULL ON UPDATE CASCADE")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "article_id", columnDefinition = "ON DELETE SET NULL ON UPDATE CASCADE")
    private Article article;
    
    public Comment() {
        
    }

    public Comment(long commentId, String commentText, Date commentDate, User user, Article article) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.user = user;
        this.article = article;
    }

    public Comment(String commentText, Date commentDate, User user, Article article) {
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.user = user;
        this.article = article;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
}
