package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leading_articles")
public class LeadingArticle implements Serializable {
    
    @Id
    @OneToOne
    @JoinColumn(name = "article_id", columnDefinition = "ON DELETE CASCADE ON UPDATE CASCADE")
    private Article article;
    
    @Column(name = "main_or_not", nullable = false)
    private boolean mainOrNot;
    
    public LeadingArticle() {
        
    }

    public LeadingArticle(Article article, boolean mainOrNot) {
        this.article = article;
        this.mainOrNot = mainOrNot;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public boolean isMainOrNot() {
        return mainOrNot;
    }

    public void setMainOrNot(boolean mainOrNot) {
        this.mainOrNot = mainOrNot;
    }
    
    
    
}
