package ch.heigvd.amt.gamification.services.email;

import javax.ejb.Local;
import javax.mail.MessagingException;

@Local
public interface IEmailSenderLocal {

    public void sendMail(String to, String subject, String body) throws MessagingException;
}
