package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.FlashBag;
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

@WebServlet(name = "makeAdminServlet", urlPatterns = {"/admin/make-admin"})
public class MakeAdminServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        FlashBag bag    = ServletUtil.getFlashBag(request);

        // promote or demote
        String action   = ServletUtil.getString(request.getParameter("action"), "promote").toLowerCase();
        Long accountId  = ServletUtil.getLong(request.getParameter("accountId"), null);
        Long currentId  = ServletUtil.getAccountId(request);

        if(!action.equals("promote") && !action.equals("demote"))
            throw new ServletException();

        if(accountId == null) {
            bag.warning("Account not found!");
        }

        else {
            try {

                Account account = accountDAO.find(accountId);
                account.setAdmin(action.equals("promote"));
                accountDAO.update(account);

                bag.info("Account " + account.getEmail() + " just got " + action + "d to admin!");

                // If demoted currently connected user, send back to profile page
                if(accountId.equals(currentId) && action.equals("demote")) {
                    response.sendRedirect(request.getContextPath() + "/developer/profile");
                    return;
                }

            } catch (EntityNotFoundException e) {
                throw new ServletException();
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/accounts");
    }
}
