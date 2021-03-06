package servlet;

import entities.Article;
import entities.LeadingArticle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataBase;

@WebServlet(name = "EditArticle", urlPatterns = {"/EditArticle"})
public class EditArticle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String result = "";
        if (request.getParameter("edit_article") != null) {

            if (!request.getParameter("title").equals("") && !request.getParameter("article_text").equals("")) { 
                Article art = new Article(Long.parseLong(request.getParameter("id")), request.getParameter("title"), request.getParameter("article_text"), 
                    new java.sql.Date(new java.util.Date().getTime()), request.getParameter("source"), request.getParameter("image"));
                LeadingArticle la = null;
                if (request.getParameter("article_type").equals("leading") || request.getParameter("article_type").equals("home"))
                    la = new LeadingArticle(art, request.getParameter("article_type").equals("leading"));
                try {
                    DataBase.updateArticle(art, request.getParameterValues("category"));
                    DataBase.updateLeadingHomeArticle(art.getId());
                    if (la != null)
                        DataBase.saveLeadingHomeArticle(la);
                    result = "success";
                } catch(Exception e) {
                    result = "error";
                }
            } else {
                result = "missing";
            }
            response.sendRedirect(request.getContextPath() + "/admin_edit_article.jsp?articleid=" + request.getParameter("id") + "&result=" + result);
            
            
        } else if (request.getParameter("cancel_edit_article") != null) {
            response.sendRedirect(request.getContextPath() + "/admin_articles.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
