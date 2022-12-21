import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Email {
    Properties props = new Properties();
    @Getter @Setter
    String sender;
    @Setter
    String senderPassword;
    @Getter @Setter
    String[] recipients;
    @Getter @Setter
    String[] cc;
    @Getter @Setter
    String[] bcc;
    @Getter @Setter
    String subject;
    @Getter @Setter
    String body;

    public Email(String host, boolean useAuth, int port, boolean useTLS, String sender, String senderPassword, String[] recipients, String[] cc, String[] bcc, String subject, String body){
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", useAuth ? "true" : "false");
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.starttls.enable", useTLS ? "true" : "false");
        this.sender = sender;
        this.senderPassword = senderPassword;
        this.recipients = recipients;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
    }
    public Email(String sender, String senderPassword, String[] recipients, String[] cc, String[] bcc, String subject, String body){
        this("smtp.gmail.com", true, 587, true, sender, senderPassword, recipients, cc, bcc, subject, body);
    }
    public Email(String sender, String senderPassword, String[] recipients, String[] cc, String subject, String body){
        this(sender, senderPassword, recipients, cc, new String[]{}, subject, body);
    }
    public Email(String sender, String senderPassword, String[] recipients, String subject, String body){
        this(sender, senderPassword, recipients, new String[]{}, subject, body);
    }
    public Email(String sender, String senderPassword, String recipient, String subject, String body){
        this(sender, senderPassword, new String[]{recipient}, subject, body);
    }
    public Email(String recipient, String subject, String body){
        this("rhys_dehaan@ryecountryday.org", SECRETDONOTCOMMITTOGITHUB.SCHOOL_PASSWORD, recipient, subject, body);
    }

    public void addRecipient(String recipient){
        String[] newRecipients = new String[recipients.length + 1];
        System.arraycopy(recipients, 0, newRecipients, 0, recipients.length);
        newRecipients[recipients.length] = recipient;
        recipients = newRecipients;
    }
    public void addCC(String recipient){
        String[] newCC = new String[cc.length + 1];
        System.arraycopy(cc, 0, newCC, 0, cc.length);
        newCC[cc.length] = recipient;
        cc = newCC;
    }
    public void addBCC(String recipient){
        String[] newBCC = new String[bcc.length + 1];
        System.arraycopy(bcc, 0, newBCC, 0, bcc.length);
        newBCC[bcc.length] = recipient;
        bcc = newBCC;
    }

    public void print(){
        System.out.println("To: " + String.join(", ", recipients));
        System.out.println("From: " + sender);
        System.out.print(cc.length == 0 ? "" : ("CC: " + String.join(", ", cc) + "\n"));
        System.out.print(bcc.length == 0 ? "" : ("BCC: " + String.join(", ", bcc) + "\n"));
        System.out.println("Subject: \033[1m" + subject + "\033[0m");
        System.out.println("Body: \n" + body);
    }
    public void send(boolean addSignature){
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            for(String recipient : recipients)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            for(String recipient : cc)
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
            for(String recipient : bcc)
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body + (addSignature ? "\n\n(This message was sent using a program written by Rhys de Haan. If there are any problems, please contact me at rhys_dehaan@ryecountyday.org.)" : ""));
            Transport.send(message);
        } catch (MessagingException e){ throw new RuntimeException(e); }
    }
}
