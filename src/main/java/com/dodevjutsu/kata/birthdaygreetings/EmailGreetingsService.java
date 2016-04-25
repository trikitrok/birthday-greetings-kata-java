package com.dodevjutsu.kata.birthdaygreetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsService implements GreetingsService {
    private final String smtpHost;
    private final int smtpPort;

    public EmailGreetingsService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void sendGreetingsTo(List<Employee> birthdayEmployees){
        try {
            for (Employee employee : birthdayEmployees) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
                        employee.getFirstName());
                String subject = "Happy Birthday!";
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject,
                        body, recipient);
            }
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed sending emails", exception);
        }
    }

    private void sendMessage(String smtpHost, int smtpPort, String sender,
                             String subject, String body, String recipient)
            throws AddressException, MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
                recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        sendMessage(msg);
    }

    // made protected for testing :-(
    protected void sendMessage(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
