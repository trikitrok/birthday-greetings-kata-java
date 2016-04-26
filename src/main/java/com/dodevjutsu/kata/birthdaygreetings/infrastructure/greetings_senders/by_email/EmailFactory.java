package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.CannotSendGreetingsException;
import com.dodevjutsu.kata.birthdaygreetings.core.GreetingMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class EmailFactory {
    private final Session session;
    private final String from;

    public EmailFactory(String from, String smtpHost, int smtpPort){
        this.from = from;
        this.session = session(smtpHost, smtpPort);
    }

    public Message composeEmailFor(GreetingMessage greetingMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(greetingMessage.to()));
            message.setSubject(greetingMessage.subject());
            message.setText(greetingMessage.text());
            return message;
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed generating emails", exception);
        }
    }

    public Session session(String smtpHost, int smtpPort) {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));
        return Session.getDefaultInstance(props, null);
    }
}
