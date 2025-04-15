package polskowniaApp.course.lecture;

import jakarta.persistence.*;

import java.time.LocalDate;

//@Entity
//@Table(name = "lectures")
public class Lecture
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate lectureDate;
    private String title;
//    @OneToMany(mappedBy = "lecture")
//    private Set<User> students;
}
