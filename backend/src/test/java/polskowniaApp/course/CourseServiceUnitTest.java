package polskowniaApp.course;

import org.junit.jupiter.api.Test;
import polskowniaApp.course.lecture.Lecture;
import polskowniaApp.user.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceUnitTest
{
    //    Set<DayOfWeek> unwrapDaysFromString(final String days)
//    {
//        var unwrappedDays = new HashSet<DayOfWeek>();
//
//        for(int i = 0; i < days.length(); i++)
//            unwrappedDays.add(DayOfWeek.of(days.charAt(i)));
//
//        return unwrappedDays;
//    }

    @Test
    void unwrapDaysFromString()
    {
//        given
        var days = "02";

//        system under test
        var toTest = new CourseService(null, null, null);

//        when
        var result = toTest.unwrapDaysFromString(days);

//        then
        System.out.println(result);

    }

//    not actual test rather try to get it working
//    @Test
//    void createLectures()
//    {
////        given
//        var startDate = LocalDate.of(2025, 5, 5);
//        var startTime = LocalTime.of(10, 0);
//        var days = "02";    // -> [poniedziałek, środa]
//        var length = 5;
//        var duration = 45;
//
//        var course = new Course(startDate, startTime, days, length, duration);
//
////        system under test
//        var toTest = new CourseService(null, null, null);
//
////        when
//        var result = toTest.createLectures(course);
//
////        then
//        System.out.println("result");
//        for (Lecture l : result)
//            System.out.println(l.getLectureDate() + " " + l.getCourse());
//        assertEquals(length, result.size());
//    }

}