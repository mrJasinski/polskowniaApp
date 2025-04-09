package polskowniaApp.lecture;

import jakarta.persistence.*;
import polskowniaApp.user.User;

import java.util.Set;

@Entity
@Table(name = "lectures")
public class Lecture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "lecture")
    private Set<User> students;
}
