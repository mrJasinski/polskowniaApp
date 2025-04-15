package polskowniaApp.user;

import org.springframework.stereotype.Service;
import polskowniaApp.user.dto.UserDTO;
import polskowniaApp.user.dto.UserLoggedDTO;

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
        return this.userRepo.findIdByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User id with given email not found!"));
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

    boolean existsByEmail(final String email)
    {
        return this.userRepo.existsByEmail(email);
    }

    User createUserByEmail(final String email)
    {
        if (existsByEmail(email))
//            TODO own exception?
            throw new IllegalArgumentException("User with given email already exists!");

        return save(generateUserByEmail(email));
    }

    private User save(final User user)
    {
        return this.userRepo.save(user);
    }

    User generateUserByEmail(final String email)
    {
//        generates user account by given email
//        generates first use password (to be changed by user after first log in)
//        also after first log in user sets system name


//        password encoding into Bcreypt?
//        and generaing
        return null;
    }


    UserLoggedDTO getLoggedUserDataByEmail(final String email, final String token)
    {
        var user = getUserByEmail(email);

        return new UserLoggedDTO(
                user.getName()
                , user.getEmail()
                , token
                , user.getRole().toString());
    }
}
