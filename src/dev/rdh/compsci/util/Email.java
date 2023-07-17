package dev.rdh.compsci.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import static dev.rdh.compsci.util.Meth.arrayAdd;

@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class Email {
    Properties props = new Properties();
    private String sender;

    public String getSender(){
        return sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }

    private String senderPassword;

    public void setSenderPassword(String senderPassword){
        this.senderPassword = senderPassword;
    }

    private String[] recipients;

    public String[] getRecipients(){
        return recipients;
    }
    public void setRecipients(String[] recipients){
        this.recipients = recipients;
    }

    private String[] cc;

    public String[] getCC(){
        return cc;
    }
    public void setCC(String[] cc){
        this.cc = cc;
    }

    private String[] bcc;

    public String[] getBCC(){
        return bcc;
    }
    public void setBCC(String[] bcc){
        this.bcc = bcc;
    }

    private String subject;

    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }

    private String body;

    public String getBody(){
        return body;
    }
    public void setBody(String body){
        this.body = body;
    }

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
        this("rhys_dehaan@ryecountryday.org", Secrets.SCHOOL_PASSWORD, recipient, subject, body);
    }

    public void addRecipient(String recipient){
        recipients = arrayAdd(recipients, recipient);
    }
    public void addCC(String recipient){
        cc = arrayAdd(cc, recipient);
    }
    public void addBCC(String recipient){
        bcc = arrayAdd(bcc, recipient);
    }


    public String toString(){
        return ("To: " + String.join(", ", recipients)) + '\n' +
               ("From: " + sender) + '\n' +
               (cc.length == 0 ? "" : ("CC: " + String.join(", ", cc) + "\n")) + '\n' +
               (bcc.length == 0 ? "" : ("BCC: " + String.join(", ", bcc) + "\n")) + '\n' +
               ("Subject: \033[1m" + subject + "\033[0m") + '\n' +
               ("Body: \n" + body);
    }
    public void send(String signature){
        try{
            var message = new MimeMessage(Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, senderPassword);
                }
            }));
            message.setFrom(new InternetAddress(sender));
            for(String recipient : recipients)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            for(String cc : cc)
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            for(String bcc : bcc)
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
            message.setSubject(subject);
            message.setText(body + (signature == null ? "" : ("\n\n" + signature)));
            Transport.send(message);
        } catch (MessagingException e){ throw new RuntimeException(e); }
    }
    public void send(){
        send(null);
    }
}
