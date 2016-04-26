package com.dodevjutsu.kata.birthdaygreetings.core;

public class Address {
    private final String value;

    public Address(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }
}
