package polskowniaApp.course;

import jakarta.persistence.*;
import polskowniaApp.course.lecture.Lecture;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refNumber;
    private String title;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDate startDate;
    private LocalTime startTime;    // lecture start time
    private String days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    @OneToMany(mappedBy = "course")
    private Set<CourseAssignment> assignments;
    @OneToMany(mappedBy = "course")
    private Set<Lecture> lectures;

    Course()
    {
    }

    Course(final LocalDate startDate, final LocalTime startTime, final String days, final int length, final int duration)
    {
        this.startDate = startDate;
        this.startTime = startTime;
        this.days = days;
        this.length = length;
        this.duration = duration;
        this.status = CourseStatus.CREATED;
    }

    Course(final String title, final Level level, final Category category, final LocalDate startDate, final LocalTime startTime, final String days, final int length, final int duration)
    {
        this.refNumber = generateRefNumber(category.getAcronym(), level, startDate, startTime);
        this.title = title;
        this.level = level;
        this.category = category;
        this.startDate = startDate;
        this.startTime = startTime;
        this.days = days;
        this.length = length;
        this.duration = duration;
        this.status = CourseStatus.CREATED;
    }

    //    materials - materiały zamieszczane przez naczyciela oraz prace przesyłane przez użytkowników oraz potem sprawdzone przez nauczyciela

    String generateRefNumber(final String acronym, final Level level, final LocalDate startDate, final LocalTime startTime)
    {
        var startDateTimeString = String.format("%s%s%s%s%s"
                , startDate.getYear()
                , startDate.getMonthValue()
                , startDate.getDayOfMonth()
                , startTime.getHour()
                , startTime.getMinute());

        return acronym + "_" + level + "_" + startDateTimeString;
    }

    List<String> getDaysWithNames()
    {
        var locale = new Locale("pl", "PL");

        var daysWithNames = new ArrayList<String>();

        for(int i = 0; i < this.days.length(); i++)
        {
            var x = Integer.parseInt(String.valueOf(this.days.charAt(i))) + 1;

            var day = DayOfWeek.of(x).getDisplayName(TextStyle.FULL, locale);

            day = day.substring(0, 1).toUpperCase() + day.substring(1);

            daysWithNames.add(day);
        }

        return daysWithNames;
    }

    void addLectures(final Set<Lecture> lectures)
    {
        this.lectures = lectures;
    }

    int getId()
    {
        return this.id;
    }

    String getRefNumber()
    {
        return this.refNumber;
    }

    String getTitle()
    {
        return this.title;
    }

    Level getLevel()
    {
        return this.level;
    }

    Category getCategory()
    {
        return this.category;
    }

    LocalDate getStartDate()
    {
        return this.startDate;
    }

    LocalTime getStartTime()
    {
        return this.startTime;
    }

    String getDays()
    {
        return this.days;
    }

    int getLength()
    {
        return this.length;
    }

    int getDuration()
    {
        return this.duration;
    }

    CourseStatus getStatus()
    {
        return this.status;
    }

    Set<Lecture> getLectures()
    {
        return this.lectures;
    }
}
