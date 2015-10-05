package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.FieldValidator;
import br.edu.ifpb.db.myplaces.core.UsersRepositoryOperations;
import br.edu.ifpb.db.myplaces.core.exceptions.CreateAccountException;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class AccountRegisterServlet extends HttpServlet {

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
        String email = request.getParameter("emailsignup");
        String username = request.getParameter("usernamesignup");
        String password = request.getParameter("passwordsignup");

        UsersRepositoryOperations operations = new UsersRepositoryOperations();

        if (FieldValidator.isNotEmpty(email, username, password)) {
            try {
                User user = operations.registerNewUser(username, email, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("admin/index.jsp");
            } catch (CreateAccountException ex) {
                request.setAttribute("error", ex.getMessage());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("error", "Todas os campos são obrigatórios");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
