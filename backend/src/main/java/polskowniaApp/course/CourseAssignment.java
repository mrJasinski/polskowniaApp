package polskowniaApp.course;

import jakarta.persistence.*;
import polskowniaApp.user.User;

@Entity
@Table(name = "course_assignments")
public class CourseAssignment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
