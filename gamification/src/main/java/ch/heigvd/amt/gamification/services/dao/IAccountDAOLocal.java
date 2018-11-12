package ch.heigvd.amt.gamification.services.dao;

import ch.heigvd.amt.gamification.Model.entity.Account;

public interface IAccountDAOLocal extends IGenericDAO<Account, Long> {

    public Account findByEmail(String email);

    public void delete(Account account) throws EntityNotFoundException;
}