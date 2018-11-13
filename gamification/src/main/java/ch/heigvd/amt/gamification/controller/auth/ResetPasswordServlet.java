package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.FlashBag;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.email.IEmailSenderLocal;
import ch.heigvd.amt.gamification.services.security.SecurityManager;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "resetPasswordServlet", urlPatterns = {"/auth/reset-password"})
public class ResetPasswordServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
    SecurityManager securityManager;

    @EJB
    IEmailSenderLocal emailSender;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email    = ServletUtil.getString(request.getParameter("email"), null);
        Integer npa     = ServletUtil.getInt(request.getParameter("npa"), null);
        String city     = ServletUtil.getString(request.getParameter("city"), null);
        FlashBag bag    = ServletUtil.getFlashBag(request);
        Long accountId  = ServletUtil.getAccountId(request);

        if(email == null || npa == null || city == null) {
            bag.warning("Missing some information");
        }
        else {

            Account account = accountDAO.findByEmail(email);

            if(account != null) {

                if(account.getNpa().equals(npa) && account.getCity().equals(city)) {
                    ResetMailThread action = new ResetMailThread(account);
                    action.start(); // Run mail sending in another thread

                    if(accountId == null)
                        bag.info("Password has been reset, check your mails!");
                    else
                        bag.info("Password for email " + email + " has been reset");
                }
                else
                    bag.warning("Npa or city not correct!");
            }

            // Account not found
            else {
                bag.danger("No account exist with email " + email);
            }
        }

        // Go back to previous page, wherever it was
        response.sendRedirect(request.getHeader("referer"));
    }

    class ResetMailThread extends Thread
    {
        private Account account;

        ResetMailThread(Account account) {
            this.account    = account;
        }

        @Override
        public void run() {
            try {
                String newPassword  = UUID.randomUUID().toString();
                account.setPassword(securityManager.hash(account, newPassword));
                accountDAO.update(account);

                // Send email
                String builder = "Hi, your password has been reset.\n" +
                        "Here is your new password: " + newPassword + "\n" +
                        "We hope too see you soon back!";

                emailSender.sendMail(account.getEmail(), "Password reinitialized", builder);

            } catch (EntityNotFoundException | MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
