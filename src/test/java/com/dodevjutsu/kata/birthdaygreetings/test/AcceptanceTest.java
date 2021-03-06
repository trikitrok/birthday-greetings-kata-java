package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.application.BirthdayService;
import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailConfiguration;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailGreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptanceTest {
    private String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 25;
    private static final String FROM = "sender@here.com";
    private List<Message> messagesSent;
    private BirthdayService service;
    private static final String EMPLOYEES_FILE_PATH = "src/test/resources/employee_data.txt";

    @Before
    public void setUp() throws Exception {
        messagesSent = new ArrayList<>();
        service = new BirthdayService(
            new FileEmployeeRepository(EMPLOYEES_FILE_PATH),
            new EmailGreetingsSender(
                new EmailConfiguration(FROM, SMTP_HOST, SMTP_PORT),
                new EmailSender() {
                    @Override
                    public void sendMessage(Message msg) {
                        messagesSent.add(msg);
                    }
                }
            )
        );
    }

    @Test
    public void sends_emails_to_employees_having_birthday_today() throws Exception {
        OurDate today = new DateRepresentation("2008/10/08").toDate();

        service.sendGreetings(today);

        assertEquals("message not sent?", 1, messagesSent.size());
        Message message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getContent());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals(1, message.getAllRecipients().length);
        assertEquals("john.doe@foobar.com", message.getAllRecipients()[0].toString());
    }

    @Test
    public void sends_no_email_when_it_is_nobodys_birthday() throws Exception {
        OurDate today = new DateRepresentation("2008/01/01").toDate();

        service.sendGreetings(
            today
        );

        assertTrue("what? messages?", messagesSent.isEmpty());
    }
}
