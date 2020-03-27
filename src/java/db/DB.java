package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entities.Article;
import entities.Category;

public class DB {
    private static Connection connection;
    
    public static void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/truth_website?useUnicode=true&characterEncoding=utf-8", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Warning! JDBC driver is missing.");
        } catch (SQLException e) {
            System.out.println("Warning! Could not connect to database server.");
        }
    }
    
    public static void closeConnection() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Warning! Connection to the database server could not be closed.");
        }
    }
    
    public static Article getLeadingArticle() {
        ResultSet rs = null;
        Article a = new Article();
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery("select * from articles where article_id in (select article_id from leading_articles where main_or_not = 1)");
            while (rs.next()) {
                a.setId(rs.getLong("article_id"));
                a.setTitle(rs.getString("article_title"));
                a.setText(rs.getString("article_text"));
                a.setSource(rs.getString("article_source"));
                a.setDate(rs.getDate("article_date"));
                a.setImage(rs.getString("article_image_path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return a;
    }
    
    public static ArrayList<Article> getHomeArticles() {
        ResultSet rs = null;
        ArrayList<Article> list = new ArrayList<Article>();
        Article a = null;
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery("select * from articles where article_id in (select article_id from leading_articles where nvl(main_or_not,-1) <> 1)");
            while (rs.next()) {
                a = new Article(rs.getLong("article_id"), rs.getString("article_title"), rs.getString("article_text"), rs.getDate("article_date"), rs.getString("article_source"), rs.getString("article_image_path"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public static ArrayList<Category> getCategories() {
        ResultSet rs = null;
        ArrayList<Category> list = new ArrayList<Category>();
        Category c = null;
        try {
            Statement s = connection.createStatement();
            rs = s.executeQuery("select * from categories");
            while (rs.next()) {
                c = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
    public static boolean addArticle(Article a) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO articles (article_title, article_text, article_date, article_source, article_image_path) VALUES ( ? , ? , ? , ? , ? )");
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getText());
            ps.setDate(3, a.getDate());
            ps.setString(4, a.getSource());
            ps.setString(5, a.getImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    
}
