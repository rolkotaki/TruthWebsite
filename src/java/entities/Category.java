package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false, columnDefinition = "TINYINT(3) UNSIGNED")
    private int id;
    
    @Column(name = "category_name", length = 25, nullable = false)
    private String name;
    
    @ManyToMany(mappedBy = "categories")
    Collection<Article> articlesOfThisTpye = new ArrayList<Article>();
        
    public Category() {
        
    }

    public Category(String name) {
        this.name = name;
    }
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Article> getArticlesOfThisTpye() {
        return articlesOfThisTpye;
    }
    
    public void setArticlesOfThisTpye(ArrayList<Article> articlesOfThisTpye) {
        this.articlesOfThisTpye = articlesOfThisTpye;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 23 * hash + this.id;
//        hash = 23 * hash + Objects.hashCode(this.name);
//        return hash;
//    }
    
    @Override
    public boolean equals(Object cat) {
        if(this == cat)
            return true;
        
        if(!(cat instanceof Category))
            return false;
        
        Category c = (Category)cat;
        return this.name.equals(c.name) && this.id == c.id;
    }
    
}
