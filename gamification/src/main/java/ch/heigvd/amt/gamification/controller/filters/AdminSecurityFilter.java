package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter
public class AdminSecurityFilter extends  BaseSecurityFilter {

    @EJB
    IAccountDAOLocal accountDAO;

    @Override
    protected String getProtectedPath() {
        return "/admin";
    }

    @Override
    Boolean accessGranted(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("ADMIN SECURITY FILTER");
        Long id = ServletUtil.getAccountId(request);
        try {
            if(id != null) {
                Account account = accountDAO.find(id);
                return account.getAdmin();
            }
            else {
                return false;
            }
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
