package com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories;

import com.dodevjutsu.kata.birthdaygreetings.core.Employee;
import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Lines {
    private final Iterator<String> linesIterator;

    Lines(Iterator<String> linesIterator) {
        this.linesIterator = linesIterator;
    }

    public List<Employee> extractEmployees() {
        List<Employee> employees = new ArrayList<>();
        while (linesIterator.hasNext()) {
            Line line = new Line(linesIterator.next());
            employees.add(line.extractEmployee());
        }
        return employees;
    }

    class Line {
        private String content;
        private final String[] tokens;

        public Line(String content) {
            this.content = content;
            this.tokens = content.split(", ");
        }

        public Employee extractEmployee() {
            return new Employee(firstName(), lastName(), birthDate(), email());
        }

        private String lastName() {
            return tokens[0];
        }

        private String email() {
            return tokens[3];
        }

        private String firstName() {
            return tokens[1];
        }

        private OurDate birthDate() {
            try {
                return new OurDate(tokens[2]);
            } catch (ParseException exception) {
                throw new CannotReadEmployeesException(
                    String.format("Badly formatted employee birth date in: '%s'", content),
                    exception
                );
            }
        }
    }
}
