package ch.heigvd.amt.gamification.services.security;

import ch.heigvd.amt.gamification.Model.entity.Account;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface IAccountCheckerLocal {

    public Map<String, String> validate(Account account);
}
