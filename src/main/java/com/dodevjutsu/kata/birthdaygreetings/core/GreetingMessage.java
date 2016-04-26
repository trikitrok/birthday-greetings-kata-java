package com.dodevjutsu.kata.birthdaygreetings.core;

public class GreetingMessage {

    private final Address to;
    private final Greeting greeting;

    private GreetingMessage(Address to, Greeting greeting) {
        this.to = to;
        this.greeting = greeting;
    }

    public static GreetingMessage generateFor(Employee employee) {
        Greeting greeting = Greeting.greetingFor(employee);
        String recipient = employee.email();
        return new GreetingMessage(new Address(recipient), greeting);
    }

    public String subject() {
        return this.greeting.header();
    }

    public String text() {
        return this.greeting.content();
    }

    public String to() {
        return this.to.asString();
    }
}
