package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Developer;
import ch.heigvd.amt.gamification.services.dao.IDeveloperDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = {"/auth/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private IDeveloperDAOLocal developerDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String target   = request.getParameter("targetUrl");

        if(target == null)
            target = "/developer/applications";
        target = request.getContextPath() + target;

        Developer developer = developerDAO.findByEmail(email);

        if(developer != null && developer.getPassword().equals(password)){
            request.getSession().setAttribute("token", developer.getId());
            response.sendRedirect(target);
        }

        request.setAttribute("error", request.getMethod().toUpperCase().equals("POST"));
        request.getRequestDispatcher("/WEB-INF/pages/auth/auth.jsp").forward(request, response);
    }
}
