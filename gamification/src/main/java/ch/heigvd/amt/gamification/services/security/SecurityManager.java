package ch.heigvd.amt.gamification.services.security;

import ch.heigvd.amt.gamification.Model.entity.Account;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

@Stateless
@Local
public class SecurityManager {

    private SecureRandom random = new SecureRandom();

    public Account createAccount() {

        Account account = new Account();
        account.setSalt(generateSalt());

        return account;
    }

    public Boolean passwordEquals(Account account, String password) {
        return Arrays.equals(hash(account, password), account.getPassword());
    }

    public byte[] hash(Account account, String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(account.getSalt());
            return md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            return password.getBytes();
        }
    }

    private byte[] generateSalt() {

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
