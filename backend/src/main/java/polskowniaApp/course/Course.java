package polskowniaApp.course;

import jakarta.persistence.*;
import polskowniaApp.course.lecture.Lecture;
import polskowniaApp.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private Set<String> days;   //weekdays when lectures are
    private int length;
    private int duration;   // lecture time [mins]
    private CourseStatus status;
    @OneToMany(mappedBy = "course")
    private Set<CourseAssignment> assignments;
    @OneToMany(mappedBy = "course")
    private Set<Lecture> lectures;
//    materials - materiały zamieszczane przez naczyciela oraz prace przesyłane przez użytkowników oraz potem sprawdzone przez nauczyciela
}
