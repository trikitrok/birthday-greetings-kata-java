package com.dodevjutsu.kata.birthdaygreetings.core;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GreetingMessage {

    private final String to;
    private final Greeting greeting;

    private GreetingMessage(String to, Greeting greeting) {
        this.to = to;
        this.greeting = greeting;
    }

    public static List<GreetingMessage> generateFor(List<Employee> employees) {
        return employees.stream().map(GreetingMessage::generateForOne).collect(toList());
    }

    private static GreetingMessage generateForOne(Employee employee) {
        Greeting greeting = Greeting.greetingFor(employee);
        String recipient = employee.email();
        return new GreetingMessage(recipient, greeting);
    }

    public String subject() {
        return this.greeting.header();
    }

    public String text() {
        return this.greeting.content();
    }

    public String to() {
        return this.to;
    }
}
