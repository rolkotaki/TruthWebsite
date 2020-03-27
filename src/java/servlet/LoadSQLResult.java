package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataBase;

@WebServlet(name = "LoadSQLResult", urlPatterns = {"/LoadSQLResult"})
public class LoadSQLResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
            HttpSession session = request.getSession(true);
            request.setAttribute("result", "");
            if (session.getAttribute("sql_statement") != null) {
                String statement = session.getAttribute("sql_statement").toString();
                List<Map<String,Object>> list = new ArrayList<>();
                if (statement.toUpperCase().replace(" ", "").startsWith("UPDATE") || statement.toUpperCase().replace(" ", "").startsWith("DELETE")) {
                    int result = DataBase.runSQLUpdate(statement);                     
                    if (result == -1)
                        request.setAttribute("result", "syntax_error");
                    else if (result == -2)
                        request.setAttribute("result", "hibernate_error");
                    else if (result == -99)
                        request.setAttribute("result", "error");
                    else {
                        request.setAttribute("result", "success");
                        Map<String,Object> m = new HashMap<>();
                        m.put("Rows " + (statement.toUpperCase().replace(" ", "").startsWith("UPDATE") ? "updated:" : "deleted:"), result);
                        list.add(m);
                        request.setAttribute("sql_resultset_list", list);
                    }
                } else {
                    list = DataBase.runSQLQuery(statement);
                    int result = 0;
                    if (list.get(list.size()-1).containsKey("error_number")) {
                        result = (int)list.get(list.size()-1).get("error_number");
                        list.remove(list.size()-1);
                    }
                    switch(result) {
                        case -1: request.setAttribute("result", "syntax_error"); break;
                        case -2: request.setAttribute("result", "hibernate_error"); break;
                        case -99: request.setAttribute("result", "error"); break;
                        default: request.setAttribute("result", "success"); break;
                    }
                    request.setAttribute("sql_resultset_list", list);
                }
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
