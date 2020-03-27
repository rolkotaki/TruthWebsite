package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constants;
import model.DataBase;
import model.PasswordEncryptor;

@WebServlet(name = "AdminChangePassword", urlPatterns = {"/AdminChangePassword"})
public class AdminChangePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
//        DataBase.setAdminInitialData();
        
        String encOldPwd = PasswordEncryptor.encryptPassword(request.getParameter("old"));
        String encNewPwd = PasswordEncryptor.encryptPassword(request.getParameter("new"));
        int result = DataBase.checkUserLogin(Constants.ADMIN_USERNAME, encOldPwd);
        switch (result) {
            case 1:
                int update = DataBase.changeUserPassword(Constants.ADMIN_USERNAME, encNewPwd);
                switch(update) {
                    case 1:
                        response.sendRedirect(request.getContextPath() + "/admin_change_pwd.jsp?result=success");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/admin_change_pwd.jsp?result=error");
                        break;
                }
                break;
            case 0:
                response.sendRedirect(request.getContextPath() + "/admin_change_pwd.jsp?result=invalid");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/admin_change_pwd.jsp?result=error");
                break;
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
