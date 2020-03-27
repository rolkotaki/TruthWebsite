package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Article;
import entities.LeadingArticle;
import model.DataBase;

@WebServlet(name = "AddArticle", urlPatterns = {"/AddArticle"})
public class AddArticle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String result = "";
        
        Article art = new Article(request.getParameter("title"), request.getParameter("article_text"), 
            new java.sql.Date(new java.util.Date().getTime()), request.getParameter("source"), request.getParameter("image"));
        LeadingArticle la = null;
        if (request.getParameter("article_type").equals("leading") || request.getParameter("article_type").equals("home"))
            la = new LeadingArticle(art, request.getParameter("article_type").equals("leading"));
        try {
            DataBase.saveArticle(art, request.getParameterValues("category"));
            if (la != null)
                DataBase.saveLeadingHomeArticle(la);
            result = "success";
        } catch(Exception e) {
            result = "error";
        }
        
        response.sendRedirect(request.getContextPath() + "/admin_add_article.jsp?result="+result);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
