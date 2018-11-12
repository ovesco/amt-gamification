package ch.heigvd.amt.gamification.services.email;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Stateless
public class EmailSender implements IEmailSenderLocal {

    /*
    @Resource(lookup = " mail/gamification")
    */
    private Session mailSession;


    public void sendMail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
        message.setSubject(subject);
        message.setText(body);
        message.setSentDate(new Date());

        Transport.send(message);
    }
}
