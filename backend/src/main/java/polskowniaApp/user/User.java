package polskowniaApp.user;

import jakarta.persistence.*;

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
//    @ManyToOne
//    @JoinColumn(name = "lecture_id")
//    private Lecture lecture;


    User()
    {
    }

    User(final String email, final String password, final UserRole role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    User(final String name, final String email, final String password, final UserRole role)
    {
        this(email, password, role);
        this.name = name;
    }

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
