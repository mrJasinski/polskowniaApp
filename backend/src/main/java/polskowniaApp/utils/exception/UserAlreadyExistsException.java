package polskowniaApp.utils.exception;

public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
