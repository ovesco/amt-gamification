package ch.heigvd.amt.gamification.controller.auth;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "tempAdminServlet", urlPatterns = {"/auth/temp-admin"})
public class TempAdminServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        try{
            Account account = accountDAO.findByEmail(email);

            if(account == null)
                response.getWriter().println("POK");
            else {
                account.setAdmin(true);
                accountDAO.update(account);
                response.getWriter().println("OK");
            }
        } catch (EntityNotFoundException e) {
            response.getWriter().println("POK");
        }
    }
}
