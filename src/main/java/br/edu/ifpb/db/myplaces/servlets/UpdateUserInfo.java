package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.UsersRepositoryOperations;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author joaomarcos
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 5)
public class UpdateUserInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        UsersRepositoryOperations uro = new UsersRepositoryOperations();
        try {
            Part part = request.getPart("image");
            user.setImage(getBytesFromPart(part));
            
            String nome = request.getParameter("name");
            int idade = Integer.parseInt(request.getParameter("age"));
            String bio = request.getParameter("bio");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            
            user.setAge(idade);
            user.setBio(bio);
            user.setName(nome);
            user.getAddress().setCity(city);
            user.getAddress().setState(state);
            user.getAddress().setCountry(country);
            //validar as informações. Se validas chama o set
            uro.updateInfo(user);
            request.getSession().setAttribute("user", uro.getUser(user.getEmail()));
            response.sendRedirect("editarinfo.jsp");
        } catch (Exception ex) {
            request.getRequestDispatcher("/admin/editarinfo.jsp").forward(request, response);
        }
    }

    public static byte[] getBytesFromPart(Part part) throws IOException {
        InputStream in = part.getInputStream();
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }

        return out.toByteArray();
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
    }// </editor-fold>

}
