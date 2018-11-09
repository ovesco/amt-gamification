package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.DeveloperValidator;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

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
    IAccountDAOLocal accountDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Account account = accountDAO.find(ServletUtil.getAccountId(request));
            request.setAttribute("dev", account);
            request.getRequestDispatcher("/WEB-INF/pages/developer/profile.jsp").forward(request, response);

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Account account = accountDAO.find(ServletUtil.getAccountId(request));
            DeveloperValidator validator = new DeveloperValidator(request, account);
            validator.populate();
            Map<String, String> errors   = validator.getErrors().size() > 0 ? validator.getErrors() : new HashMap<String, String>();

            request.setAttribute("dev", account);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/account/profile.jsp").forward(request, response);

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }
}
