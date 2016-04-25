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
    public void sendGreetingsTo(List<Employee> birthdayEmployees){
        try {
            for (Employee employee : birthdayEmployees) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
                        employee.getFirstName());
                String subject = "Happy Birthday!";
                Message message = buildMessage("sender@here.com", subject, body, recipient);
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
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getDefaultInstance(props, null);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
