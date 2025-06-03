package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String _token = request.getParameter("_token");
        
        if (_token != null && _token.equals(request.getSession().getId())) {
            
            EntityManager em = DBUtil.createEntityManager();
            
            Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));
            
            String content = request.getParameter("content");
            t.setContent(content);
            
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);
            
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            
            request.getSession().removeAttribute("task_id");
            
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
