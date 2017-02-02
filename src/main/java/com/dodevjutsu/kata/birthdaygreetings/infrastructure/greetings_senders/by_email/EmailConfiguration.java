package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

public class EmailConfiguration {
    private int smtpPort;
    private String smtpHost;
    private String from;

    public EmailConfiguration(String from, String smtpHost, int smtpPort) {
        this.from = from;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    int smtpPort() {
        return smtpPort;
    }

    String smtpHost() {
        return smtpHost;
    }

    String from() {
        return from;
    }
}
