package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Developer;
import ch.heigvd.amt.gamification.Util.DeveloperValidator;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Developer developer = new Developer();
        developer.setFirstName("Guillaume");
        developer.setLastName("Hochet");
        developer.setEmail("guillaume.hochet@gmail.com");
        developer.setStreet("Planches 1");
        developer.setNpa(1073);
        developer.setCity("Savigny");
        developer.setPassword("yoloswag22");

        DeveloperValidator validator = new DeveloperValidator(request, developer);
        validator.populate();
        Map<String, String> errors   = validator.getErrors().size() > 0 ? validator.getErrors() : new HashMap<String, String>();

        request.setAttribute("dev", developer);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/WEB-INF/pages/developer/profile.jsp").forward(request, response);
    }
}
