package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.FieldValidator;
import br.edu.ifpb.db.myplaces.core.JsonResult;
import br.edu.ifpb.db.myplaces.core.PostRepositoryOperations;
import br.edu.ifpb.db.myplaces.entitys.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class NewPostServlet extends HttpServlet {

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
        User logged = (User) request.getSession().getAttribute("user");
        String author = logged.getEmail();
        String username = logged.getName();
        String place = request.getParameter("place");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String post = request.getParameter("post");
        Date date = Calendar.getInstance().getTime();

        JsonResult result = new JsonResult(false, "Faltando informaçoes");
        if (FieldValidator.isNotEmpty(author, username, place, lat, lng, post)) {
            result.setMsg("Salvo");
            result.setSucess(true);
            PostRepositoryOperations pro = new PostRepositoryOperations();
            pro.newPost(author, username, post, place, Double.parseDouble(lat), Double.parseDouble(lng));
        }
        response.getWriter().print(new Gson().toJson(result));

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
