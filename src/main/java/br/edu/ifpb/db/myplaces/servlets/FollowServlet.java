package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.JsonResult;
import br.edu.ifpb.db.myplaces.core.PostRepositoryOperations;
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
public class FollowServlet extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String op = request.getParameter("op");
        UsersRepositoryOperations operations = new UsersRepositoryOperations();
        User user = (User) request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        if (op.equals("verify")) {
            JsonResult result = new JsonResult(true, operations.isFollower(user.getEmail(), email) ? "true" : "false");
            response.getWriter().print(new Gson().toJson(result));
        } else if (op.equals("qtde")) {
            JsonResult result = new JsonResult(true, operations.numberOfFollowers(email) + "");
            response.getWriter().print(new Gson().toJson(result));
        } else {
            boolean status = operations.isFollower(user.getEmail(), email);
            if (status == false) {
                operations.followAnUser(user.getEmail(), email);
            } else {
                operations.unfollowAnUser(user.getEmail(), email);
            }
            JsonResult result = new JsonResult(true, status ? "unfollow" : "follow");
            response.getWriter().print(new Gson().toJson(result));
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
