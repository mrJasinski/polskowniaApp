package polskowniaApp.user.dto;

import polskowniaApp.user.UserRole;

public class UserDTO
{
    private String name;
    private String email;
    private String password;
    private UserRole role;

    UserDTO()
    {
    }

    public UserDTO(final String email, final String password)
    {
        this.email = email;
        this.password = password;
    }

    public UserDTO(final String email, final String password, final UserRole role)
    {
        this(email, password);
        this.role = role;
    }

    UserDTO(final String name, final String email, final String password, final UserRole role)
    {
        this(email, password, role);
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public UserRole getRole()
    {
        return this.role;
    }
}
