package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.security.SecurityManager;

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
    private IAccountDAOLocal developerDAO;

    @EJB
    private SecurityManager securityManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email    = ServletUtil.getString(request.getParameter("email"), null);
        String password = ServletUtil.getString(request.getParameter("password"), null);
        String target   = ServletUtil.getString(request.getParameter("targetUrl"), null);
        String error    = null;
        
        if(target == null)
            target = "/developer/applications";
        target = request.getContextPath() + target;

        if(email != null) {

            Account account = developerDAO.findByEmail(email);

            if(account != null && securityManager.passwordEquals(account, password)){
                ServletUtil.setAccountId(request, account.getId());
                response.sendRedirect(target);
            }
            else
                error = "Invalid credentials";
        }

        else
            error = "Empty email provided";

        if(error != null) {
            request.setAttribute("error", error);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/WEB-INF/pages/auth/login.jsp").forward(request, response);
        }
    }
}
