package polskowniaApp.course.dto;

import polskowniaApp.course.lecture.LectureReadModel;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CourseReadModel
{
    private String refNumber;
    private String title;
    private Level level;
    private String category;
    private LocalDate startDate;
    private LocalTime startTime;    // lecture start time
    private List<String> days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]
    private String status;
    private List<LectureReadModel> lectures;

    CourseReadModel()
    {
    }

    public CourseReadModel(
            final String refNumber
            , final String title
            , final Level level
            , final String category
            , final LocalDate startDate
            , final LocalTime startTime
            , final List<String> days
            , final int length
            , final int duration
            , final String status
            , final List<LectureReadModel> lectures)
    {
        this.refNumber = refNumber;
        this.title = title;
        this.level = level;
        this.category = category;
        this.startDate = startDate;
        this.startTime = startTime;
        this.days = days;
        this.length = length;
        this.duration = duration;
        this.status = status;
        this.lectures = lectures;
    }

    public String getRefNumber()
    {
        return this.refNumber;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Level getLevel()
    {
        return this.level;
    }

    public String getCategory()
    {
        return this.category;
    }

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public List<String> getDays()
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

    public String getStatus()
    {
        return this.status;
    }

    public List<LectureReadModel> getLectures()
    {
        return this.lectures;
    }
}
