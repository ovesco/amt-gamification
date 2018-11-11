package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.DeveloperValidator;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.security.SecurityManager;
import ch.heigvd.amt.gamification.services.session.IFlashBagLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "registerServlet", urlPatterns = {"/auth/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private IAccountDAOLocal DeveloperDAO;

    @EJB
    private IFlashBagLocal flashBag;

    @EJB
    private SecurityManager manager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DeveloperValidator validator = new DeveloperValidator(request, manager.createAccount());
        String password = validator.verifyPassword();
        validator.populate();

        Account account = validator.getDeveloper();
        account.setPassword(manager.hash(account, password));

        // No errors, go to auth
        if(validator.getErrors().size() == 0) {
            DeveloperDAO.create(account);
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }

        // Errors found, show back form
        else {
            Map<String, String> errors = request.getMethod().toUpperCase().equals("GET")
                    ? new HashMap<String, String>()
                    : validator.getErrors();

            flashBag.warning("Some errors appeared, please review form");

            request.setAttribute("dev", account);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/auth/register.jsp").forward(request, response);
        }
    }
}
