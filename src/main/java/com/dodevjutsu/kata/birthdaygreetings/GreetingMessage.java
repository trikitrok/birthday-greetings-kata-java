package com.dodevjutsu.kata.birthdaygreetings;

public class GreetingMessage {

    private final Address address;
    private final Greeting greeting;

    public GreetingMessage(Address address, Greeting greeting) {
        this.address = address;
        this.greeting = greeting;
    }

    public String subject() {
        return greeting.header();
    }

    public String text() {
        return greeting.content();
    }

    public String address() {
        return address.value();
    }
}
