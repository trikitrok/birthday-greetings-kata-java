package com.dodevjutsu.kata.birthdaygreetings;

import javax.mail.MessagingException;

public class CannotSendGreetingsException extends RuntimeException {
    public CannotSendGreetingsException(String cause, MessagingException exception) {
        super(cause, exception);
    }
}
