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


    public List<Employee> whoseBirthdayIsOn(OurDate ourDate) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new CannotReadEmployeesException("file was not found", e);
        }
        String str = "";
        List<Employee> birthdayEmployees = new ArrayList();
        try {
            str = in.readLine(); // skip header

            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        employeeData[2], employeeData[3]);
                if (employee.isBirthday(ourDate)) {
                    birthdayEmployees.add(employee);
                }
            }
        } catch (IOException e) {
            throw new CannotReadEmployeesException("cannot read file", e);
        } catch (ParseException e) {
            throw new CannotReadEmployeesException(String.format("cannot parse employee: '%s'", str), e);
        }
        return birthdayEmployees;
    }
}
