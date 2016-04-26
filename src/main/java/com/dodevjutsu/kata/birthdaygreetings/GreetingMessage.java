package com.dodevjutsu.kata.birthdaygreetings;

public class GreetingMessage {

    private final Address address;
    private final Greeting message;

    public GreetingMessage(Address address, Greeting message) {
        this.address = address;
        this.message = message;
    }

    public String subject() {
        return message.subject();
    }

    public String text() {
        return message.content();
    }

    public String address() {
        return address.value();
    }
}
