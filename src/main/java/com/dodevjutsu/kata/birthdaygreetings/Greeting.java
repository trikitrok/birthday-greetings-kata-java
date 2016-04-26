package com.dodevjutsu.kata.birthdaygreetings;

public class Greeting {
    private final String header;
    private final String content;

    public Greeting(String header, String content) {
        this.header = header;
        this.content = content;
    }

    public static Greeting greetingFor(Employee employee){
        String content = String.format("Happy Birthday, dear %s!", employee.getFirstName());
        String header = "Happy Birthday!";

        return new Greeting(header, content);
    }

    public String header() {
        return header;
    }

    public String content() {
        return content;
    }
}
