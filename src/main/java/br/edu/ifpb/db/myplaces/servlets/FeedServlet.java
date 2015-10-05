package br.edu.ifpb.db.myplaces.servlets;

import br.edu.ifpb.db.myplaces.core.Feed;
import br.edu.ifpb.db.myplaces.core.JsonResult;
import br.edu.ifpb.db.myplaces.entitys.Post;
import br.edu.ifpb.db.myplaces.entitys.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarcos
 */
public class FeedServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Feed feed = new Feed();
        List<Post> news = feed.update((User) request.getSession().getAttribute("user"));        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String sizeList = ""+news.size();
        JsonResult result = new JsonResult(true, sizeList);
        request.getSession().setAttribute("feed", news);
        response.getWriter().print(new Gson().toJson(result));
        System.out.println(news.get(0).getComments().size());
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
