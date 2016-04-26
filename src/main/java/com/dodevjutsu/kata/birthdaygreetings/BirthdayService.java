package com.dodevjutsu.kata.birthdaygreetings;

import java.util.ArrayList;
import java.util.List;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private GreetingsService greetingsService;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingsService greetingsService) {
        this.employeeRepository = employeeRepository;
        this.greetingsService = greetingsService;
    }

    public void sendGreetings(OurDate ourDate) {
        List<Employee> birthdayEmployees = getEmployeesHavingBirthdayOn(ourDate);
        sendMessages(generateGreetings(birthdayEmployees));
    }

    private List<GreetingMessage> generateGreetings(List<Employee> employees) {
        ArrayList<GreetingMessage> greetingMessages = new ArrayList<>();
        for (Employee employee : employees) {
            final GreetingMessage greetingMessage = generateGreetingMessage(employee);
            greetingMessages.add(greetingMessage);
        }
        return greetingMessages;
    }

    private GreetingMessage generateGreetingMessage(Employee employee) {
        Greeting greeting = Greeting.greetingFor(employee);
        String recipient = employee.getEmail();
        return new GreetingMessage(new Address(recipient), greeting);
    }

    private void sendMessages(List<GreetingMessage> birthdayEmployees) {
        greetingsService.sendGreetingsTo(birthdayEmployees);
    }

    private List<Employee> getEmployeesHavingBirthdayOn(OurDate ourDate) {
        return employeeRepository.whoseBirthdayIsOn(ourDate);
    }

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
                new FileEmployeeRepository("employee_data.txt"),
                new EmailGreetingsService("localhost", 25));
        try {
            service.sendGreetings(new OurDate("2008/10/08"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
