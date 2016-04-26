package com.dodevjutsu.kata.birthdaygreetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsSender implements GreetingsSender {
    private final Session session;

    public EmailGreetingsSender(String smtpHost, int smtpPort) {
        session = obtainSession(smtpHost, smtpPort);
    }

    @Override
    public void send(List<GreetingMessage> messages){
        for (GreetingMessage message : messages) {
            sendMessage(generateEmail(message));
        }
    }

    private Message generateEmail(GreetingMessage message) {
        final String from = "sender@here.com";

        Message result;
        try {
            result = new MimeMessage(session);
            result.setFrom(new InternetAddress(from));
            result.setRecipient(Message.RecipientType.TO, new InternetAddress(message.address()));
            result.setSubject(message.subject());
            result.setText(message.text());
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed generating emails", exception);
        }
        return result;

    }

    private Session obtainSession(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        return Session.getDefaultInstance(props, null);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) {
        try {
            Transport.send(msg);
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed sending emails", exception);
        }
    }
}
