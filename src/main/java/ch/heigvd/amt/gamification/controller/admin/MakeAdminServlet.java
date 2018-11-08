package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
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

        // promote or demote
        String action   = ServletUtil.getString(request.getParameter("action"), "promote").toLowerCase();
        Long accountId  = ServletUtil.getLong(request.getParameter("accountId"), null);

        if(!action.equals("promote") && !action.equals("demote"))
            throw new ServletException();

        try {

            Account account = accountDAO.find(accountId);
            account.setAdmin(action.equals("promote"));
            accountDAO.update(account);

            response.sendRedirect(request.getContextPath() + "/admin/accounts");

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }
}
