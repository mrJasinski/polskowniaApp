package polskowniaApp.course;

import jakarta.persistence.*;
import polskowniaApp.course.lecture.Lecture;
import polskowniaApp.user.User;

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

    //    materials - materiały zamieszczane przez naczyciela oraz prace przesyłane przez użytkowników oraz potem sprawdzone przez nauczyciela

    List<String> getDaysWithNames()
    {
        var locale = new Locale("pl", "PL");

        var daysWithNames = new ArrayList<String>();

        for(int i = 0; i < days.length(); i++)
        {
            var x = Integer.parseInt(String.valueOf(days.charAt(i))) + 1;

            var day = DayOfWeek.of(x).getDisplayName(TextStyle.FULL, locale);

            day = day.substring(0, 1).toUpperCase() + day.substring(1);

            daysWithNames.add(day);
        }

        return daysWithNames;
    }


    int getId()
    {
        return this.id;
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

    void addLectures(final Set<Lecture> lectures)
    {
        this.lectures = lectures;
    }


}
