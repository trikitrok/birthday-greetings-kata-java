package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OurDateTest {
    @Test
    public void identifies_if_two_dates_were_in_the_same_day() throws Exception {
        OurDate ourDate = new DateRepresentation("1789/01/24").toDate();
        OurDate sameDay = new DateRepresentation("2001/01/24").toDate();
        OurDate notSameDay = new DateRepresentation("1789/01/25").toDate();
        OurDate notSameMonth = new DateRepresentation("1789/02/25").toDate();

        assertTrue("same", ourDate.isSameDay(sameDay));
        assertFalse("not same day", ourDate.isSameDay(notSameDay));
        assertFalse("not same month", ourDate.isSameDay(notSameMonth));
    }
}
