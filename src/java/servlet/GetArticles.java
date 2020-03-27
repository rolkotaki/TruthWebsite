package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ArticlePageListHelper;

@WebServlet(name = "GetArticles", urlPatterns = {"/GetArticles"})
public class GetArticles extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        ArticlePageListHelper plh = new ArticlePageListHelper((request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page")));
        if (session.getAttribute("session_type") != null && (int)session.getAttribute("session_type") == 0) {
            plh.buildDataRestricted(session.getAttribute("session_restriction").toString());
            request.setAttribute("session_lastpage", plh.getLastPage());
            request.setAttribute("session_pagelist", plh.getPageList());
            request.setAttribute("session_article_list", plh.getArticleList());
            request.setAttribute("session_type", 0);
            
        } else {
            plh.buildDataAll();
            request.setAttribute("session_lastpage", plh.getLastPage());
            request.setAttribute("session_pagelist", plh.getPageList());
            request.setAttribute("session_article_list", plh.getArticleList());
            request.setAttribute("session_type", 1);
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
