package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.FieldValidator;
import br.edu.ifpb.db.myplaces.core.UsersRepositoryOperations;
import br.edu.ifpb.db.myplaces.core.exceptions.LoginException;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class Login extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if(FieldValidator.isNotEmpty(email, password)){
            try {
                UsersRepositoryOperations uro = new UsersRepositoryOperations();
                User user = uro.login(email, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("admin/index.jsp");
            } catch (LoginException ex) {
                request.setAttribute("error", ex.getMessage());
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("error", "Os dados informados precisam ser validos!");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
