// 新規作成サーブレット

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
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();
        
        Task t = new Task();
        
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);
        
        String content = "nice to meet you";
        t.setContent(content);
        
        em.persist(t);
        em.getTransaction().commit();
        
        response.getWriter().append(Integer.valueOf(t.getId()).toString());
        
        em.close();
    }

}
