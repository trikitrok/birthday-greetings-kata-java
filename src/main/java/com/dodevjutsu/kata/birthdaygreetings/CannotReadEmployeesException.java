package com.dodevjutsu.kata.birthdaygreetings;

public class CannotReadEmployeesException extends RuntimeException {
    public CannotReadEmployeesException(String cause, Exception exception) {
        super(cause, exception);
    }
}
