package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

import com.dodevjutsu.kata.birthdaygreetings.core.GreetingMessage;
import com.dodevjutsu.kata.birthdaygreetings.core.GreetingsSender;

import java.util.List;

public class EmailGreetingsSender implements GreetingsSender {
    private static final String FROM = "sender@here.com";
    private final EmailSender emailSender;
    private final EmailFactory emailFactory;

    public EmailGreetingsSender(String smtpHost, int smtpPort, EmailSender emailSender) {
        this.emailSender = emailSender;
        this.emailFactory = new EmailFactory(FROM, smtpHost, smtpPort);
    }

    @Override
    public void send(List<GreetingMessage> messages){
        for (GreetingMessage message : messages) {
            emailSender.sendMessage(
                emailFactory.composeEmailFor(message)
            );
        }
    }
}
