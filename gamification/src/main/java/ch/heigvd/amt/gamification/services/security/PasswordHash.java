package ch.heigvd.amt.gamification.services.security;

import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class PasswordHash {

    public static String hash(String password, String salt) {
        try {
            MessageDigest md    = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));

            byte[] bytes        = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb    = new StringBuilder();

            for (byte aByte : bytes)
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
