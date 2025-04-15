package polskowniaApp.course;

import jakarta.persistence.*;
import polskowniaApp.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "courses")
class Course
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
//    private Set<User> participants;
//    materials - materiały zamieszczane przez naczyciela oraz prace przesyłane przez użytkowników oraz potem sprawdzone przez nauczyciela
}
