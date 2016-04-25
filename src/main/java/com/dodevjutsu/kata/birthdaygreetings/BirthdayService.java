package com.dodevjutsu.kata.birthdaygreetings;

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
        sendGreetingsTo(birthdayEmployees);
    }

    private void sendGreetingsTo(List<Employee> birthdayEmployees) {
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
