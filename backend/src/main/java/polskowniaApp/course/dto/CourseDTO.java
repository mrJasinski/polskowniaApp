package polskowniaApp.course.dto;

import polskowniaApp.course.CourseStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class CourseDTO
{
    private LocalDate startDate;
    private LocalTime startTime;    // lecture start time
    private Set<String> days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]
    private CourseStatus status;

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public Set<String> getDays()
    {
        return this.days;
    }

    public int getLength()
    {
        return this.length;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public CourseStatus getStatus()
    {
        return this.status;
    }
}
