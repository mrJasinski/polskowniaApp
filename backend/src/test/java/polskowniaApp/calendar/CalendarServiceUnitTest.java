package polskowniaApp.calendar;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarServiceUnitTest
{
    @Test
    void test()
    {
        var givenDate = LocalDate.now();

        var date = givenDate.getDayOfWeek().equals(DayOfWeek.MONDAY) ? givenDate : givenDate.minusDays(givenDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());

        var xDate = givenDate.plusDays(DayOfWeek.SUNDAY.getValue() - givenDate.getDayOfWeek().getValue());

        System.out.println("sun " + xDate.getDayOfWeek());

        System.out.println("given " + givenDate.getDayOfWeek().getValue());
        System.out.println("--- " + givenDate.minusDays(givenDate.getDayOfWeek().getValue()).getDayOfWeek().getValue());

        System.out.println(date.getDayOfWeek() + " " + date.getDayOfWeek().getValue());
    }

}