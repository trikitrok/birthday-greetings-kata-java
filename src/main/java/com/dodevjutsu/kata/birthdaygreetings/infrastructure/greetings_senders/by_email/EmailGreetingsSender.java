package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

import com.dodevjutsu.kata.birthdaygreetings.core.GreetingMessage;
import com.dodevjutsu.kata.birthdaygreetings.core.GreetingsSender;

import java.util.List;

public class EmailGreetingsSender implements GreetingsSender {
    private static final String FROM = "sender@here.com";
    private final EmailSender emailSender;
    private final EmailComposer emailComposer;

    public EmailGreetingsSender(String smtpHost, int smtpPort, EmailSender emailSender) {
        this.emailSender = emailSender;
        this.emailComposer = new EmailComposer(FROM, smtpHost, smtpPort);
    }

    @Override
    public void send(List<GreetingMessage> messages){
        for (GreetingMessage message : messages) {
            emailSender.sendMessage(
                emailComposer.composeEmailFor(message)
            );
        }
    }
}
