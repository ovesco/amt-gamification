package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Account;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountDAO extends GenericDAO<Account, Long> implements IAccountDAOLocal {

    @Override
    public Account findByEmail(String email) {
        List<Account> result = em.createNamedQuery("Account.findByEmail").setParameter("email", email).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }
}
