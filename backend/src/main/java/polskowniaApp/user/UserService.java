package polskowniaApp.user;

import org.springframework.stereotype.Service;
import polskowniaApp.user.dto.UserDTO;

import java.util.NoSuchElementException;

@Service
public class UserService
{
    private final UserRepository userRepo;

    public UserService(final UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    public int getUserIdByEmail(final String email)
    {
//        TODO
        return 0;
    }

    User getUserByEmail(final String email)
    {
        return this.userRepo.findByEmail(email)
            .orElseThrow(() -> new NoSuchElementException("User with given email not found!"));
    }

    public UserDTO getUserByEmailAsDto(final String email)
    {
        return toDto(getUserByEmail(email));
    }

    UserDTO toDto(final User user)
    {
        return new UserDTO(user.getEmail(), user.getPassword(), user.getRole());
    }


}
