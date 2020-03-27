package servlet;

import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataBase;
import model.PasswordEncryptor;

@WebServlet(name = "Registrate", urlPatterns = {"/Registrate"})
public class Registrate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String result = "";
        String password = PasswordEncryptor.encryptPassword(request.getParameter("pwd"));
        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setEmailId(request.getParameter("email_id"));
        user.setGender(request.getParameter("gender").equals("Male"));
        user.setBirthYear(Integer.parseInt(request.getParameter("birth_year")));
        user.setPassword(password);
        if (DataBase.saveUser(user)) {
            result = "success";
        } else {
            result = "error";
        }
        response.sendRedirect(request.getContextPath() + "/registration.jsp?result="+result);
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
