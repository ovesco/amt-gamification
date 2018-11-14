package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TempAdminServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = (String)request.getAttribute("email");
        try{
            Account account = accountDAO.findByEmail(email);
            account.setAdmin(true);
            accountDAO.update(account);
            response.getWriter().println("OK");
        } catch (EntityNotFoundException e) {
            response.getWriter().println("POK");
        }
    }
}
