package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Developer;
import ch.heigvd.amt.gamification.Util.DeveloperValidator;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IDeveloperDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "profileServlet", urlPatterns = {"/developer/profile"})
public class ProfileServlet extends HttpServlet {

    @EJB
    IDeveloperDAOLocal developerDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Developer developer = developerDAO.find(ServletUtil.getDevId(request));
            request.setAttribute("dev", developer);
            request.getRequestDispatcher("/WEB-INF/pages/developer/profile.jsp").forward(request, response);

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Developer developer = developerDAO.find(ServletUtil.getDevId(request));
            DeveloperValidator validator = new DeveloperValidator(request, developer);
            validator.populate();
            Map<String, String> errors   = validator.getErrors().size() > 0 ? validator.getErrors() : new HashMap<String, String>();

            request.setAttribute("dev", developer);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/developer/profile.jsp").forward(request, response);

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }
}
