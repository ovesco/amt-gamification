package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.email.IEmailSenderLocal;
import ch.heigvd.amt.gamification.services.security.SecurityManager;
import ch.heigvd.amt.gamification.services.session.IFlashBagLocal;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "resetPasswordServlet", urlPatterns = {"/admin/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
    SecurityManager securityManager;

    @EJB
    IEmailSenderLocal emailSender;

    @EJB
    IFlashBagLocal flashBag;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email    = ServletUtil.getString(request.getParameter("email"), null);
        Integer npa     = ServletUtil.getInt(request.getParameter("npa"), null);
        String city     = ServletUtil.getString(request.getParameter("city"), null);

        if(email == null || npa == null || city == null) {
            response.getWriter().println("No email could be found for ");
        }

        try {
            Account account     = accountDAO.findByEmail(email);
            String newPassword  = UUID.randomUUID().toString();
            account.setPassword(securityManager.hash(account, newPassword));
            accountDAO.update(account);

            // Send email
            String builder = "Hi, your password has been reset.\n" +
                    "Here is your new password: " + newPassword + "\n" +
                    "We hope too see you soon back!";

            emailSender.sendMail(account.getEmail(), "Password reinitialized", builder);

            // Not logged in, show message email sent
            if(ServletUtil.getAccountId(request) == null) {
                flashBag.info("Password for email " + account.getEmail() + " has been reset, check your emails!");
                response.sendRedirect(request.getContextPath() + "/auth/login");
            }

            else {
                flashBag.info("Password for email " + account.getEmail() + " has been reset");
                response.sendRedirect(request.getContextPath() + "/admin/accounts");
            }

        } catch (EntityNotFoundException | MessagingException e) {
            throw new ServletException(e);
        }
    }
}
