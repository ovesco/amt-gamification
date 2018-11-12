package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.session.IFlashBagLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteAccountServlet", urlPatterns = {"/admin/delete"})
public class DeleteAccountServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
    IFlashBagLocal flashbag;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long accountId  = ServletUtil.getLong(request.getParameter("accountId"), null);
        Long currentId  = ServletUtil.getAccountId(request);

        if(accountId != null) {
            try {
                Account account = accountDAO.find(accountId);
                accountDAO.delete(account);
                flashbag.info("Account successfully deleted");

                // Logout
                if(currentId.equals(accountId)) {
                    response.sendRedirect(request.getContextPath() + "/auth/logout");
                    return;
                }

            } catch (EntityNotFoundException e) {
                // Do nothing
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/accounts");
    }
}
