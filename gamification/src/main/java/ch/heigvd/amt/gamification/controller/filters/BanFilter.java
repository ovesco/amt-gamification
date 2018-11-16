package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BanFilter extends BaseSecurityFilter {

    @EJB
    IAccountDAOLocal accountDAO;

    @Override
    protected String getProtectedPath() {
        return "";
    }

    @Override
    Boolean accessGranted(HttpServletRequest request, HttpServletResponse response) {

        Long currentId  = ServletUtil.getAccountId(request);

        if(currentId == null)
            return true; // Well, user is not banned

        try {
            Account account = accountDAO.find(currentId);
            if(account.getBanned()) {
                ServletUtil.getFlashBag(request).warning("You have been banned!");
                return false;
            }

            return true;
        } catch (EntityNotFoundException e) {
            // No user exists, invalidate session
            return false;
        }
    }
}
