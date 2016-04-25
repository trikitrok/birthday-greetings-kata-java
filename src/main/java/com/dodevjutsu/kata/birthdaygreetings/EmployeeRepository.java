package com.dodevjutsu.kata.birthdaygreetings;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> whoseBirthdayIsOn(OurDate ourDate);
}
