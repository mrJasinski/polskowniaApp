package polskowniaApp.user.dto;

public class UserReadModel
{
    private String name;
    private String email;

    public UserReadModel()
    {
    }

    public UserReadModel(final String name, final String email)
    {
        this.name = name;
        this.email = email;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }
}
