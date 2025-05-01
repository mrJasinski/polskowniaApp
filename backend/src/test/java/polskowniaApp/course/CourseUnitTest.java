package polskowniaApp.course;

import org.junit.jupiter.api.Test;

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
}