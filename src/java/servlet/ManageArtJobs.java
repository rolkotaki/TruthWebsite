package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ManageArtJobs", urlPatterns = {"/ManageArtJobs"})
public class ManageArtJobs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        if (request.getParameter("add") != null) {
            response.sendRedirect(request.getContextPath() + "/admin_add_article.jsp");
        } 
        else if (request.getParameter("list_all") != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("session_type", 1);
            session.setAttribute("session_restriction", null);
            response.sendRedirect(request.getContextPath() + "/admin_articles.jsp");
        } else if (request.getParameter("list_by_restriction") != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("session_restriction", request.getParameter("restriction"));
            session.setAttribute("session_type", 0);
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
