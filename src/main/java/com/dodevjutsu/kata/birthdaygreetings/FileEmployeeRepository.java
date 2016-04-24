package com.dodevjutsu.kata.birthdaygreetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository {
    private final String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }


    public List<Employee> whoseBirthdayIsOn(OurDate ourDate) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        List<Employee> birthdayEmployees = new ArrayList();
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0],
                    employeeData[2], employeeData[3]);
            if (employee.isBirthday(ourDate)) {
                birthdayEmployees.add(employee);
            }
        }
        return birthdayEmployees;
    }
}
