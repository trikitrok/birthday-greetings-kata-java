package com.dodevjutsu.kata.birthdaygreetings;

import java.util.List;

import static java.util.stream.Collectors.*;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private GreetingsSender greetingsSender;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingsSender greetingsSender) {
        this.employeeRepository = employeeRepository;
        this.greetingsSender = greetingsSender;
    }

    public void sendGreetings(OurDate date) {
        send(greetingMessagesFor(employeesHavingBirthdayOn(date)));
    }

    private List<GreetingMessage> greetingMessagesFor(List<Employee> employees) {
        return employees.stream().map(GreetingMessage::generateFor).collect(toList());
    }

    private void send(List<GreetingMessage> greetingMessages) {
        greetingsSender.send(greetingMessages);
    }

    private List<Employee> employeesHavingBirthdayOn(OurDate ourDate) {
        return employeeRepository.whoseBirthdayIsOn(ourDate);
    }

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
                new FileEmployeeRepository("employee_data.txt"),
                new EmailGreetingsSender("localhost", 25));
        try {
            service.sendGreetings(new OurDate("2008/10/08"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
