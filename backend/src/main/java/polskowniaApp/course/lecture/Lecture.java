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

    public Lecture(final LocalDate lectureDate)
    {
        this.lectureDate = lectureDate;
    }
}
