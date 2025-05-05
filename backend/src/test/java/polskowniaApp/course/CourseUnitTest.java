package polskowniaApp.course;

import org.junit.jupiter.api.Test;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CourseUnitTest
{
//    List<String> getDaysWithNames()
//    {
//        var locale = new Locale("pl", "PL");
//
//        var daysWithNames = new ArrayList<String>();
//
//        for(int i = 0; i < days.length(); i++)
//        {
//            var x = Integer.parseInt(String.valueOf(days.charAt(i))) + 1;
//            daysWithNames.add(DayOfWeek.of(x).getDisplayName(TextStyle.FULL, locale));
//        }
//
//        return daysWithNames;
//    }

    @Test
    void getDaysWithNames_shouldReturnLocalDayOfWeekNames()
    {
//        given
        var days = "024";   // -> [pon, śr, pt]
//        system under test
        var toTest = new Course(null, null, days, 0, 0);

//        when
        var result = toTest.getDaysWithNames();

//        then
        assertEquals(3, result.size());
        for (String s : result)
            System.out.println(s);

        assertTrue(result.contains("Poniedziałek"));
        assertTrue(result.contains("Środa"));
        assertTrue(result.contains("Piątek"));
    }

//    String generateRefNumber(final String acronym, final Level level, final LocalDate startDate, final LocalTime startTime)
//    {
//        var startDateTimeString = String.format("%s%s%s%s%s"
//                , startDate.getYear()
//                , startDate.getMonthValue()
//                , startDate.getDayOfMonth()
//                , startTime.getHour()
//                , startTime.getMinute());
//
//        return acronym + "_" + level + "_" + startDateTimeString;
//    }

    @Test
    void generateRefNumber_shouldReturnCourseRefNumberFromCategoryAcronymAndLevelAndStartDateAndTime()
    {
//        given
        var acronym = Category.COURSE_INDIVIDUAL.getAcronym();
        var level = Level.B2;
        var startDate = LocalDate.of(2025, 5,5);
        var startTime = LocalTime.of(10, 0);

        var expected = String.format("%s_%s_%s%s%s%s%s", acronym, level, startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), startTime.getHour(), startTime.getMinute());

//        system under test
        var toTest = new Course();

//        when
        var result = toTest.generateRefNumber(acronym, level, startDate, startTime);

//        then
        assertEquals(expected, result);
    }
}