package com.dodevjutsu.kata.birthdaygreetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsService implements GreetingsService {
    private final Session session;

    public EmailGreetingsService(String smtpHost, int smtpPort) {
        session = obtainSession(smtpHost, smtpPort);
    }

    @Override
    public void sendGreetingsTo(List<Employee> employees){
        try {
            for (Employee current : employees) {
                String recipient = current.getEmail();
                String body = String.format("Happy Birthday, dear %s!", current.getFirstName());
                String subject = "Happy Birthday!";
                final String from = "sender@here.com";
                Message message = buildMessage(from, subject, body, recipient);
                sendMessage(message);
            }
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed sending emails", exception);
        }
    }

    private Message buildMessage(String sender, String subject, String body, String recipient) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(body);
        return message;
    }

    private Session obtainSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        return Session.getDefaultInstance(props, null);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
