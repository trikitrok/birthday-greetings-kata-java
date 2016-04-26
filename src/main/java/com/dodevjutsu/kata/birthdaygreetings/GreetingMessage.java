package com.dodevjutsu.kata.birthdaygreetings;

public class GreetingMessage {

    private final Address address;
    private final Greeting greeting;

    private GreetingMessage(Address address, Greeting greeting) {
        this.address = address;
        this.greeting = greeting;
    }

    public static GreetingMessage generateFor(Employee employee) {
        Greeting greeting = Greeting.greetingFor(employee);
        String recipient = employee.getEmail();
        return new GreetingMessage(new Address(recipient), greeting);
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
