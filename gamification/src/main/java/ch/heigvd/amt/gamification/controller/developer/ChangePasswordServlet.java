package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.FlashBag;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.security.SecurityManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "changePasswordServlet", urlPatterns = {"/developer/change-password"})
public class ChangePasswordServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
    SecurityManager manager;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String oldPassword  = ServletUtil.getString(request.getParameter("oldPassword"),  null);
        String newPassword  = ServletUtil.getString(request.getParameter("newPassword"), null);
        String confirm      = ServletUtil.getString(request.getParameter("confirm"), null);
        FlashBag bag        = ServletUtil.getFlashBag(request);
        String error        = null;
        Long currentId      = ServletUtil.getAccountId(request);

        try {
            Account account = accountDAO.find(currentId);

            if(oldPassword == null || newPassword == null || confirm == null)
                error = "Missing some information";

            else {
                if(!manager.passwordEquals(account, oldPassword))
                    error = "Wrong password";
                if(oldPassword.equals(newPassword))
                    error = "Please enter a different new password";
                if(!newPassword.equals(confirm))
                    error = "New password and confirmation do not match!";
                if(newPassword.length() < 8)
                    error = "Password must be at least 8 characters long";
            }

            if(error == null) {
                account.setPassword(manager.hash(account, newPassword));
                account.setForceChangePassword(false);
                accountDAO.update(account);
                bag.success("Password updated!");
                response.sendRedirect(request.getContextPath() + "/developer/profile");
            }

            else {
                if(request.getMethod().equalsIgnoreCase("POST"))
                    ServletUtil.getFlashBag(request).warning(error);

                request.getRequestDispatcher("/WEB-INF/pages/developer/changePassword.jsp").forward(request, response);
            }

        } catch (EntityNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
