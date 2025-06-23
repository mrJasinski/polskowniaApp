package polskowniaApp.calendar;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
class CalendarService
{

    //Map<String, List<String[]>> getCalendarDataByDate(final String date)
    CalendarData getCalendarDataByDate(final String date)
    {
        var givenDate = LocalDate.parse(date);

//        czy daa się to wyciągnąc do metody? obudować odejmowanie Math.abs()

        var weekStartDate = givenDate.getDayOfWeek().equals(DayOfWeek.MONDAY) ? givenDate : givenDate.minusDays(givenDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        var weekEndDate = givenDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) ? givenDate : givenDate.plusDays(DayOfWeek.SUNDAY.getValue() - givenDate.getDayOfWeek().getValue());

//        wyciągnąć z bazy kursy które trwają podczas tygodnia wyznaczonego tymi datami
//        dla tych kursów wyciągnąć dni tygodnia godzinę rozpoczęcia zajęć oraz czas trwania

//        hardcode dla wstępnych testów

        var day1 = new DayData("Poniedziałek", "10:00", "11:00");

        return new CalendarData(weekStartDate, weekEndDate, List.of(day1));
    }
}
