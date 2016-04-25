package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.BirthdayService;
import com.dodevjutsu.kata.birthdaygreetings.EmailGreetingsService;
import com.dodevjutsu.kata.birthdaygreetings.FileEmployeeRepository;
import com.dodevjutsu.kata.birthdaygreetings.OurDate;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest {

    private static final int SMTP_PORT = 25;
    private List<Message> messagesSent;
    private BirthdayService service;
    private String smtpHost = "localhost";

    @Before
    public void setUp() throws Exception {
        messagesSent = new ArrayList<Message>();

        service = new BirthdayService(new FileEmployeeRepository("src/test/resources/employee_data.txt"),
                new EmailGreetingsService(smtpHost, SMTP_PORT) {
            @Override
            protected void sendMessage(Message msg) throws MessagingException {
                messagesSent.add(msg);
            }});
    }

    @Test
    public void baseScenario() throws Exception {

        service.sendGreetings(
                new OurDate("2008/10/08"));

        assertEquals("message not sent?", 1, messagesSent.size());
        Message message = messagesSent.get(0);
        assertEquals("Happy Birthday, dear John!", message.getContent());
        assertEquals("Happy Birthday!", message.getSubject());
        assertEquals(1, message.getAllRecipients().length);
        assertEquals("john.doe@foobar.com",
                message.getAllRecipients()[0].toString());
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        service.sendGreetings(
                new OurDate("2008/01/01"));

        assertEquals("what? messages?", 0, messagesSent.size());
    }
}
