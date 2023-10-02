package org.example.util;

import java.time.LocalDate;
import java.time.Period;

public class PeriodWithoutWeekends {
    public static int getWorkingDaysDifference(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        int totalDays = years * 365 + months * 30 + days;
        return totalDays - countWeekendDays(startDate, endDate);
    }
    private static int countWeekendDays(LocalDate startDate, LocalDate endDate) {
        int weekendDays = 0;

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek().getValue() >= 6) {
                weekendDays++;
            }
        }

        return weekendDays;
    }
}
