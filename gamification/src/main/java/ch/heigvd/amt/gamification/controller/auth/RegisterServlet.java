package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.security.IAccountCheckerLocal;
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
    private IAccountDAOLocal accountDAO;

    @EJB
    private IFlashBagLocal flashBag;

    @EJB
    private SecurityManager manager;

    @EJB
    private IAccountCheckerLocal accountChecker;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Account account = manager.createAccount();
        String password = accountChecker.getPassword(request);
        accountChecker.populate(request, account);

        Map<String, String> errors  = new HashMap<>();
        accountChecker.validateNotEmpty(errors, account);
        accountChecker.validatePassword(errors, password);
        accountChecker.checkEmailTaken(errors, account.getEmail(), null);

        // No errors, go to auth
        if(errors.size() == 0) {

            account.setPassword(manager.hash(account, password));
            accountDAO.create(account);
            flashBag.success("Account created, please login");
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }

        // Errors found, show back form
        else {

            Map<String, String> problems = request.getMethod().equalsIgnoreCase("GET")
                    ? new HashMap<String, String>() : errors;

            for(String field : problems.keySet())
                System.out.println("CONTAINS: " + field);

            flashBag.warning("Some errors appeared, please review form");
            request.setAttribute("dev", account);
            request.setAttribute("errors", problems);
            request.getRequestDispatcher("/WEB-INF/pages/auth/register.jsp").forward(request, response);
        }
    }
}
