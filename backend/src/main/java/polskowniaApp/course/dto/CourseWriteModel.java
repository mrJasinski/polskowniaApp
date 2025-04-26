package polskowniaApp.course.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class CourseWriteModel
{
    private LocalDate startDate;
    private LocalTime startTime;    // lecture start time
    private Set<Integer> days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]

    CourseWriteModel()
    {
    }

    CourseWriteModel(final LocalDate startDate, final LocalTime startTime, final Set<Integer> days, final int length, final int duration)
    {
        this.startDate = startDate;
        this.startTime = startTime;
        this.days = days;
        this.length = length;
        this.duration = duration;
    }

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public Set<Integer> getDays()
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
}
