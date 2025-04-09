package polskowniaApp.user;

import jakarta.persistence.*;
import polskowniaApp.lecture.Lecture;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    int getId()
    {
        return this.id;
    }

    String getName()
    {
        return this.name;
    }

    String getEmail()
    {
        return this.email;
    }

    String getPassword()
    {
        return this.password;
    }

    UserRole getRole()
    {
        return this.role;
    }
}
