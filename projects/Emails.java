import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Emails {
    public static void sendEmail(String[] recipients, String sender, String senderPassword, String subject, String body, String[] cc, String[] bcc){
        Properties props = new Properties();
        // send emails using a gmail account
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587"); // DO NOT CHANGE 587 THIS IS AN IMPORTANT NUMBER
        props.put("mail.smtp.starttls.enable", "true");
        // create a session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword); // note: if it errors on this line, make sure you have enabled "less secure apps" in your gmail account or have a 2FA enabled and an app specific password for this program
            }
        });
        try {
            // create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender)); // set the sender
            for(String recipient : recipients) // set the recipients
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            for(String recipient : cc) // set the cc recipients
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(recipient));
            for(String recipient : bcc) // set the bcc recipients
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body + "\n\n(This message was sent using a program written by Rhys de Haan. If there are any problems, please contact me at rhys_dehaan@ryecountyday.org.)");
            // send the message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendEmail(String[] recipients, String sender, String senderPassword, String subject, String body){
        sendEmail(recipients, sender, senderPassword, subject, body, new String[]{}, new String[]{});
    }
    public static void sendEmail(String recipient, String sender, String senderPassword, String subject, String body){
        sendEmail(new String[]{recipient}, sender, senderPassword, subject, body, new String[]{}, new String[]{});
    }
}
