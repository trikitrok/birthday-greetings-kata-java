package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.core.Employee;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {

    @Test
    public void testBirthday() throws Exception {
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

    @Test
    public void equality() throws Exception {
        Employee base = new Employee(
            "First", "Last",
            new DateRepresentation("1999/09/01").toDate(),
            "first@last.com");
        Employee same = new Employee(
            "First", "Last",
            new DateRepresentation("1999/09/01").toDate(),
            "first@last.com");
        Employee different = new Employee(
            "First", "Last",
            new DateRepresentation("1999/09/01").toDate(),
            "boom@boom.com");

        assertFalse(base.equals(null));
        assertFalse(base.equals(""));
        assertTrue(base.equals(same));
        assertFalse(base.equals(different));
    }
}
