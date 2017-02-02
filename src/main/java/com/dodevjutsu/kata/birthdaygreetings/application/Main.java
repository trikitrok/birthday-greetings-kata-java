package com.dodevjutsu.kata.birthdaygreetings.application;

import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailConfiguration;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailGreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;

public class Main {
    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
            new FileEmployeeRepository("employee_data.txt"),
            new EmailGreetingsSender(new EmailConfiguration("sender@here.com", "localhost", 25), new EmailSender())
        );
        try {
            service.sendGreetings(
                new DateRepresentation("2008/10/08").toDate()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
