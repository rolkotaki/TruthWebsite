package model;

import entities.Article;
import java.util.ArrayList;
import java.util.List;

public class ArticlePageListHelper {

    private List<Article> articleList;
    private ArrayList<Integer> pageList;
    private int lastPage;
    private int page;
    
    
    public ArticlePageListHelper(int page) {
        this.page = page;
    }
    
    public void buildDataAll() {
        int resultNum = DataBase.getAllArticlesNumber();
        setPageList(getPage(), resultNum);
        setLastPage((int)Math.ceil((1.0 * resultNum / Constants.MAX_ARTICLES_ON_PAGE)));
        setArticleList(DataBase.getAllArticles(getPage()));
    }
    
    public void buildDataRestricted(String restriction) {
        int resultNum = DataBase.getArticlesNumber(restriction);        
        setPageList(getPage(), resultNum);
        setLastPage((int)Math.ceil((1.0 * resultNum / Constants.MAX_ARTICLES_ON_PAGE)));
        setArticleList(DataBase.getArticles(getPage(), restriction));      
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public ArrayList<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(int page, int resultNum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (page < 5 || page > (int)Math.ceil((1.0 * resultNum / Constants.MAX_ARTICLES_ON_PAGE)) - 4) 
            page = 5;

        int i = -4;
        while (((int)Math.ceil((1.0 * resultNum / Constants.MAX_ARTICLES_ON_PAGE)) >= (page + i)) && i <= 4) {
            list.add(page + i);
            i++;
        }
        pageList = list;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
}
