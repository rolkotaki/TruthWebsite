package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
@MultipartConfig
public class UploadImage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        if (!request.getPart("image").getSubmittedFileName().equals("")) {
            Part image = request.getPart("image");
            String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            String uploadPath = request.getServletContext().getRealPath("");
            uploadPath += File.separator + "images";
            File saveDir = new File(uploadPath);
            if (!saveDir.exists())
                saveDir.mkdirs();
            try {
                image.write(uploadPath + File.separator + fileName);
                response.sendRedirect(request.getContextPath() + "/admin_images.jsp?result=success");
            } catch (IOException e) {
                response.sendRedirect(request.getContextPath() + "/admin_images.jsp?result=error");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin_images.jsp?result=missing");
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
