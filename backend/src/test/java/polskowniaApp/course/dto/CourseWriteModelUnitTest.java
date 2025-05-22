package polskowniaApp.course.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseWriteModelUnitTest
{
//    LocalTime parseToLocalTime(String time)
//    {
//        var index = time.indexOf(':');
//        var hours = Integer.parseInt(time.substring(0, index));
//        var mins = Integer.parseInt(time.substring(index));
//
//        return LocalTime.of(hours, mins);
//    }

    @Test
    void parseToLocalTime_shouldReturnLocalTimeExtractedFromString()
    {
//        given
        var hours = 10;
        var mins = 30;
        var time = hours + ":" + mins;

//        system under test
        var toTest = new CourseWriteModel();

//        when
        var result = toTest.parseToLocalTime(time);

//        then
        System.out.println(result);
        assertEquals(LocalTime.of(hours, mins), result);
    }

}