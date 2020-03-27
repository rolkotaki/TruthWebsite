package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ManageImage", urlPatterns = {"/ManageImage"})
public class ManageImage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String result = "";
        String path = request.getServletContext().getRealPath("/images");
        if (request.getParameter("delete") != null) {
            File image = new File(path + File.separator + request.getParameter("list"));
            if (image.delete())
                result = "deleted";
            else
                result = "deleteError";
        }
        String restriction = request.getParameter("restriction");
        ArrayList<String> resultList = new ArrayList<String>();
        File imageFolder = new File(path);
        String[] list = imageFolder.list();
        for (String f : list)  {
            if (f.matches("(.*)" + restriction + "(.*)"))
                resultList.add(f);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("imageList", resultList);
        session.setAttribute("restriction", restriction);
        response.sendRedirect(request.getContextPath() + "/admin_images.jsp" + (result.equals("") ? "" : "?result="+result));
        
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
