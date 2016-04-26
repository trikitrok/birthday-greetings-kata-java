package com.dodevjutsu.kata.birthdaygreetings;

import java.util.List;

public interface GreetingsSender {
    void send(List<GreetingMessage> messages);
}
