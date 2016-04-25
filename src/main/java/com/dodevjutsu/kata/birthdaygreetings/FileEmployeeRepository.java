package com.dodevjutsu.kata.birthdaygreetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {
    private final String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public List<Employee> whoseBirthdayIsOn(OurDate ourDate) {
        String str = "";
        List<Employee> birthdayEmployees = new ArrayList();
        try {
            Iterator<String> iterator = readFileSkipHeader();

            while (iterator.hasNext()) {
                str = iterator.next();
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0],
                        employeeData[2], employeeData[3]);
                if (employee.isBirthday(ourDate)) {
                    birthdayEmployees.add(employee);
                }
            }
        } catch (IOException e) {
            throw new CannotReadEmployeesException("cannot read file = " + Paths.get(fileName).toAbsolutePath(), e);
        } catch (ParseException e) {
            throw new CannotReadEmployeesException(String.format("cannot parse employee: '%s'", str), e);
        }
        return birthdayEmployees;
    }

    private Iterator<String> readFileSkipHeader() throws IOException {
        return new MyFileReader(Paths.get(fileName)).skipHeader();
    }
}
