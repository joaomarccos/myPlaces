package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.JsonResult;
import br.edu.ifpb.db.myplaces.core.PostRepositoryOperations;
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
public class LikeServlet extends HttpServlet {

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
        PostRepositoryOperations pro = new PostRepositoryOperations();
        User user = (User) request.getSession().getAttribute("user");
        String postId = request.getParameter("postid");
        if (op.equals("verify")) {
            JsonResult result = new JsonResult(true, pro.isLikedFor(postId, user.getEmail()) ? "true" : "false");
            response.getWriter().print(new Gson().toJson(result));
        }else if(op.equals("qtde")){
            JsonResult result = new JsonResult(true, pro.numberOfLikes(postId)+"");
            response.getWriter().print(new Gson().toJson(result));
        }else {
            boolean status = pro.isLikedFor(postId, user.getEmail());
            if (status == false) {
                pro.likePost(user.getEmail(), postId);
            }else{
                pro.dislikePost(user.getEmail(), postId);
            }
            JsonResult result = new JsonResult(true, status ? "dislike" : "like");
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
