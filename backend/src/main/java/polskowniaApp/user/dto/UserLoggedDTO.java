package polskowniaApp.user.dto;

public class UserLoggedDTO
{
    private String email;
    private String token;

    public UserLoggedDTO(final String email, final String token)
    {
        this.email = email;
        this.token = token;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getToken()
    {
        return this.token;
    }
}
