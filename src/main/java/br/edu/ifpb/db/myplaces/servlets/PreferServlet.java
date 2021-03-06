package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.UsersRepositoryOperations;
import br.edu.ifpb.db.myplaces.entitys.User;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class PreferServlet extends HttpServlet {

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
        UsersRepositoryOperations operations = new UsersRepositoryOperations();
        User user = (User) request.getSession().getAttribute("user");
        String theme = request.getParameter("theme");
        String font = request.getParameter("font");
        String op = request.getParameter("op");        
        if (op.equals("get")) {
            response.getWriter().print(new Gson().toJson(operations.getTheme(user)));
        } else {
            operations.changeUserTheme(user, font, theme);
            response.getWriter().print(new Gson().toJson(operations.getTheme(user)));
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
    }// </editor-fold>

}
