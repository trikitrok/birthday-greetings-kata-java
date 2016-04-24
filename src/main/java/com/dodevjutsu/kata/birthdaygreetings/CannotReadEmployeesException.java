package com.dodevjutsu.kata.birthdaygreetings;

import java.io.FileNotFoundException;

public class CannotReadEmployeesException extends RuntimeException {
    public CannotReadEmployeesException(String cause, Exception exception) {
    }
}
