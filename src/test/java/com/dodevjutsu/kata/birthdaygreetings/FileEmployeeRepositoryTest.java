package com.dodevjutsu.kata.birthdaygreetings;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class FileEmployeeRepositoryTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void fails_when_the_file_does_not_exist() throws Exception {

        FileEmployeeRepository service = new FileEmployeeRepository("non-existing.file");
        expected.expect(CannotReadEmployeesException.class);
        expected.expectMessage(containsString("cannot read file"));
        expected.expectMessage(containsString("non-existing.file"));

        service.whoseBirthdayIsOn(new OurDate("2016/01/01"));
    }

    @Test
    public void fails_when_the_file_does_not_have_the_necessary_fields() throws Exception {

        FileEmployeeRepository service = new FileEmployeeRepository("src/test/resources/wrong_data__wrong-date-format.csv");
        expected.expect(CannotReadEmployeesException.class);
        expected.expectMessage(containsString("cannot parse employee"));

        service.whoseBirthdayIsOn(new OurDate("2016/01/01"));
    }

}