package com.dodevjutsu.kata.birthdaygreetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsService implements GreetingsService {
    private final String smtpHost;
    private final int smtpPort;
    private final Session session;

    public EmailGreetingsService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        session = configureProperties(smtpHost, smtpPort);
    }

    @Override
    public void sendGreetingsTo(List<Employee> birthdayEmployees){
        try {
            for (Employee employee : birthdayEmployees) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
                        employee.getFirstName());
                String subject = "Happy Birthday!";
                sendMessage("sender@here.com", subject,
                        body, recipient);
            }
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed sending emails", exception);
        }
    }

    private void sendMessage(String sender, String subject, String body, String recipient)
            throws MessagingException {

        Message msg = buildMessage(sender, subject, body, recipient);

        sendMessage(msg);
    }

    private Message buildMessage(String sender, String subject, String body, String recipient) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
                recipient));
        msg.setSubject(subject);
        msg.setText(body);
        return msg;
    }

    private Session configureProperties(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getDefaultInstance(props, null);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
