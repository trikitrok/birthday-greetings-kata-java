package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.core.Employee;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {
    @Test
    public void knows_the_date_of_its_birthday() throws Exception {
        Employee employee = new Employee(
            "foo", "bar",
            new DateRepresentation("1990/01/31").toDate(),
            "a@b.c");

        assertFalse("not his birthday",
            employee.isBirthday(
                new DateRepresentation("2008/01/30").toDate()
            )
        );

        assertTrue("his birthday",
            employee.isBirthday(
                new DateRepresentation("2008/01/31").toDate()
            )
        );
    }
}
