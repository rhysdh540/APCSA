package dev.rdh.compsci.util;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@FieldDefaults(level = AccessLevel.PRIVATE) @SuppressWarnings({"unused", "FieldMayBeFinal"})
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
    public static <T> T[] arrayAdd(T[] arr, T toAdd) {
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[arr.length] = toAdd;
        return newArr;
    }

    public void print(){
        System.out.println("To: " + String.join(", ", recipients));
        System.out.println("From: " + sender);
        System.out.print(cc.length == 0 ? "" : ("CC: " + String.join(", ", cc) + "\n"));
        System.out.print(bcc.length == 0 ? "" : ("BCC: " + String.join(", ", bcc) + "\n"));
        System.out.println("Subject: \033[1m" + subject + "\033[0m");
        System.out.println("Body: \n" + body);
    }
    public void send(String signature){
        var session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword);
            }
        });
        try{
            var message = new MimeMessage(session);
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
