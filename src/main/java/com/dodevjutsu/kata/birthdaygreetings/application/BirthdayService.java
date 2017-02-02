package com.dodevjutsu.kata.birthdaygreetings.application;

import com.dodevjutsu.kata.birthdaygreetings.core.*;

import java.util.List;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private GreetingsSender greetingsSender;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingsSender greetingsSender) {
        this.employeeRepository = employeeRepository;
        this.greetingsSender = greetingsSender;
    }

    public void sendGreetings(OurDate today) {
        send(greetingMessagesFor(employeesHavingBirthday(today)));
    }

    private List<GreetingMessage> greetingMessagesFor(List<Employee> employees) {
        return GreetingMessage.generateFor(employees);
    }

    private void send(List<GreetingMessage> greetingMessages) {
        greetingsSender.send(greetingMessages);
    }

    private List<Employee> employeesHavingBirthday(OurDate today) {
        return employeeRepository.whoseBirthdayIs(today);
    }
}
