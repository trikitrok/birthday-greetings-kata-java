package com.dodevjutsu.kata.birthdaygreetings.test;

import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.DateRepresentation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OurDateTest {
    @Test
    public void getters() throws Exception {
        OurDate ourDate = new DateRepresentation("1789/01/24").convertToDate();
        assertEquals(1, ourDate.getMonth());
        assertEquals(24, ourDate.getDay());
    }

    @Test
    public void isSameDate() throws Exception {
        OurDate ourDate = new DateRepresentation("1789/01/24").convertToDate();
        OurDate sameDay = new DateRepresentation("2001/01/24").convertToDate();
        OurDate notSameDay = new DateRepresentation("1789/01/25").convertToDate();
        OurDate notSameMonth = new DateRepresentation("1789/02/25").convertToDate();

        assertTrue("same", ourDate.isSameDay(sameDay));
        assertFalse("not same day", ourDate.isSameDay(notSameDay));
        assertFalse("not same month", ourDate.isSameDay(notSameMonth));
    }

    @Test
    public void equality() throws Exception {
        OurDate base = new DateRepresentation("2000/01/02").convertToDate();
        OurDate same = new DateRepresentation("2000/01/02").convertToDate();
        OurDate different = new DateRepresentation("2000/01/04").convertToDate();

        assertFalse(base.equals(null));
        assertFalse(base.equals(""));
        assertTrue(base.equals(base));
        assertTrue(base.equals(same));
        assertFalse(base.equals(different));
    }
}
