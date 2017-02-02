package com.dodevjutsu.kata.birthdaygreetings.application;

import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailConfiguration;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailGreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;

public class Main {

    private static final String EMPLOYEES_FILE_PATH = "employee_data.txt";
    private static final String SENDER_EMAIL_ADDRESS = "sender@here.com";
    private static final String HOST = "localhost";
    private static final int SMTP_PORT = 25;
    private static final String TODAY_AS_STRING = "2008/10/08";

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
            new FileEmployeeRepository(EMPLOYEES_FILE_PATH),
            new EmailGreetingsSender(
                new EmailConfiguration(SENDER_EMAIL_ADDRESS, HOST, SMTP_PORT),
                new EmailSender()
            )
        );
        try {
            OurDate today = new DateRepresentation(TODAY_AS_STRING).toDate();
            service.sendGreetings(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
