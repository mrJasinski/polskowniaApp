package polskowniaApp.course.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class CourseWriteModel
{
    private LocalDate startDate;
    private LocalTime startTime;    // lecture start time
    private Set<Integer> days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]
    private Set<String> participants;   // participants email adressess

    CourseWriteModel()
    {
    }

    CourseWriteModel(final LocalDate startDate, final String startTime, final Set<Integer> days, final int length, final int duration)
    {
        this.startDate = startDate;
        this.startTime = parseToLocalTime(startTime);
        this.days = days;
        this.length = length;
        this.duration = duration;
    }

    CourseWriteModel(final LocalDate startDate, final String startTime, final Set<Integer> days, final int length, final int duration, final Set<String> participants)
    {
        this.startDate = startDate;
        this.startTime = parseToLocalTime(startTime);
        this.days = days;
        this.length = length;
        this.duration = duration;
        this.participants = participants;
    }

    LocalTime parseToLocalTime(String time)
    {
        var index = time.indexOf(':');
        var hours = Integer.parseInt(time.substring(0, index));
        var mins = Integer.parseInt(time.substring(index + 1));

        return LocalTime.of(hours, mins);
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

    public Set<String> getParticipants()
    {
        return this.participants;
    }
}
