package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.Feed;
import br.edu.ifpb.db.myplaces.core.FieldValidator;
import br.edu.ifpb.db.myplaces.core.PostRepositoryOperations;
import br.edu.ifpb.db.myplaces.entitys.User;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class CommentPostServlet extends HttpServlet {

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
        PostRepositoryOperations pro = new PostRepositoryOperations();
        String comment = request.getParameter("comment");
        String postid = request.getParameter("postid");
        User user = (User) request.getSession().getAttribute("user");
        if (FieldValidator.isNotEmpty(comment)) {
            pro.commentPost(postid, comment, Calendar.getInstance().getTime(), user.getEmail(), user.getName());            
        }
        Feed feed = new Feed();
        request.getSession().setAttribute("feed", feed.update(user));
        response.sendRedirect("index.jsp#"+postid);
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
