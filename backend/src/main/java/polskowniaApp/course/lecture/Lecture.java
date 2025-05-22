package polskowniaApp.course.lecture;

import jakarta.persistence.*;
import polskowniaApp.course.Course;

import java.time.LocalDate;

@Entity
@Table(name = "lectures")
public class Lecture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate lectureDate;
    private String title;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Lecture()
    {
    }

    public Lecture(final LocalDate lectureDate, final Course course)
    {
        this.title = "";
        this.lectureDate = lectureDate;
        this.course = course;
    }

    public int getId()
    {
        return this.id;
    }

    public LocalDate getLectureDate()
    {
        return this.lectureDate;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Course getCourse()
    {
        return this.course;
    }
}
