package helperClasses;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static helperClasses.Hooks.props;

/**
 * Created by Sharikov Pavel on 14.10.2017.
 */
public class SentMail {

    public static void sendMessageToMailBox(String subjectMessage, String bodyMessage) throws MessagingException {
        Hooks.stash.put("subjectMessage", subjectMessage);
        Hooks.stash.put("bodyMessage", bodyMessage);
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(props.getProperty("yandexSentLogin"),
                                props.getProperty("yandexSentPassword"));
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(props.getProperty("yandexSentEmail")));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(props.getProperty("yandexEmail")));
        message.setSubject(subjectMessage);
        message.setText(bodyMessage);
        Transport.send(message);
    }
}