package com.dodevjutsu.kata.birthdaygreetings;

public class Greeting {
    private final String subject;
    private final String body;

    public Greeting(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String content() {
        return body;
    }

    public String subject() {
        return subject;
    }
}
