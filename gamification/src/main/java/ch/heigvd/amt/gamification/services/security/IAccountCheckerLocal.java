package ch.heigvd.amt.gamification.services.security;

import ch.heigvd.amt.gamification.Model.entity.Account;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Local
public interface IAccountCheckerLocal {

    public void validateNotEmpty(Map<String, String> errors, Account account);

    public void validatePassword(Map<String, String> errors, String password);

    public void checkEmailTaken(Map<String, String> errors, String email, String current);

    public void populate(HttpServletRequest request, Account account);

    public String getPassword(HttpServletRequest request);
}
