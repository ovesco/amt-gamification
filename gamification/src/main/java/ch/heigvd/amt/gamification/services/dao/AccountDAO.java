package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Model.entity.Application;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountDAO extends GenericDAO<Account, Long> implements IAccountDAOLocal {

    @EJB
    IApplicationDAOLocal applicationDAO;

    @Override
    public Account findByEmail(String email) {
        List<Account> result = em.createNamedQuery("Account.findByEmail").setParameter("email", email).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }

    public void delete(Account account) throws EntityNotFoundException {

        for(Object item : em.createNamedQuery("Application.findByDeveloper").setParameter("id", account.getId()).getResultList())
            applicationDAO.delete((Application)item);

        super.delete(account);
    }
}
