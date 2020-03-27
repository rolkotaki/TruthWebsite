package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private long id;
    
    @Column(name = "article_title", length = 300, nullable = false)
    private String title;
    
    @Column(name = "article_text")
    private String text;
    
    @Column(name = "article_date")
    private Date date;
    
    @Column(name = "article_source", length = 50)
    private String source;
    
    @Column(name = "article_image_path", length = 300)
    private String image;
    
    @ManyToMany
    @JoinTable(name = "articles_categories", joinColumns = {@JoinColumn(name = "article_id", columnDefinition = "ON DELETE SET NULL ON UPDATE CASCADE")}, inverseJoinColumns = {@JoinColumn(name = "category_id", columnDefinition = "ON DELETE SET NULL ON UPDATE CASCADE")})
    private Collection<Category> categories = new ArrayList<Category>();
    
    public Article() {
        
    }

    public Article(long id, String title, String text, Date date, String source, String image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.source = source;
        this.image = image;
    }
    
    public Article(String title, String text, Date date, String source, String image) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.source = source;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public Collection<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    
    public String getCategoryList() {
        String list = "";
        for (Category c : getCategories())
            list += ", " + c.getName();
        if (getCategories().size() == 0)
            return "";
        list = list.substring(2);
        return list;
    }
    
}
