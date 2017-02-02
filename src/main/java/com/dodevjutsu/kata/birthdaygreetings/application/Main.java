package com.dodevjutsu.kata.birthdaygreetings.application;

import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailConfiguration;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailGreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
            new FileEmployeeRepository("employee_data.txt"),
            new EmailGreetingsSender(new EmailConfiguration("sender@here.com", "localhost", 25), new EmailSender())
        );
        try {
            service.sendGreetings(
                new OurDate(
                    new SimpleDateFormat("yyyy/MM/dd").parse("2008/10/08")
                )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
