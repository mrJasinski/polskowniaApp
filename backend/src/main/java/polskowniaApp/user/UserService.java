package polskowniaApp.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import polskowniaApp.mail.MailService;
import polskowniaApp.user.dto.UserDTO;
import polskowniaApp.user.dto.UserLoggedDTO;
import polskowniaApp.user.dto.UserReadModel;
import polskowniaApp.utils.exception.UserAlreadyExistsException;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService
{
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final MailService mailService;

    public UserService(final UserRepository userRepo, final PasswordEncoder encoder, final MailService mailService)
    {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.mailService = mailService;
    }

    public int getUserIdByEmail(final String email)
    {
        return this.userRepo.findIdByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User id with given email not found!"));
    }

    public User getUserByEmail(final String email)
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
        return new UserDTO(
                user.getEmail()
                , user.getPassword()
                , user.getRole());
    }

    boolean existsByEmail(final String email)
    {
        return this.userRepo.existsByEmail(email);
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

        //send registration email? with link to password change

        var user = new User(
                ""
                , email
                , this.encoder.encode(passwordGenerator())
                , UserRole.STUDENT);

        this.mailService.sendRegistrationMail(user.getEmail(), user.getPassword());

        return user;
    }

    String passwordGenerator()
    {
        final String LOWER = "abcdefghijklmnopqrstuvwxyz";
        final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String DIGITS = "0123456789";
        final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

        StringBuilder password = new StringBuilder();
        var random = new SecureRandom();

        var chars = LOWER + UPPER + DIGITS + PUNCTUATION;

//        8 jako założona z góry długość hasła
        var passwordLength = 8;
        for (int i = 0; i < passwordLength; i++)
        {
            var index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        // dodatkowe sprawdzenie czy hasło zawiera duży znak mały znak liczbę oraz znak specjalny? ogółem spełnia wymogi

        return password.toString();
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

    public void createUserAccountByEmail(final String email)
    {
        if (!existsByEmail(email))
            save(generateUserByEmail(email));
    }

    public List<Integer> getUserIdsByEmails(final Set<String> emails)
    {
        return this.userRepo.findIdsByEmails(emails);
    }

    List<User> getStudents()
    {
        return this.userRepo.findByRole(UserRole.STUDENT);
    }

    List<UserReadModel> getStudentsAsReadModel()
    {
        return getStudents()
                .stream()
                .map(User::toReadModel)
                .toList();
    }

    void restoreForgottenPassword(final String email)
    {
//        odzyskiwanie zapomnianego hasła i generowanie nowego przez system
        var newPass = passwordGenerator();

        changePasswordByEmail(email, newPass);

        this.mailService.sendPasswordResetMail(email, newPass);
    }

    void changePasswordByEmail(final String email, final String password)
    {
//        zmiana hasła przez użytkownika na własnoręcznie wybrane

        this.userRepo.updatePasswordByEmail(email, this.encoder.encode(password));
    }

    User createUser(final UserDTO dto)
    {
        if (this.userRepo.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistsException("User with given email already exists!");

        return this.userRepo.save(new User(
                dto.getName()
                , dto.getEmail()
                , this.encoder.encode(dto.getPassword())
                , UserRole.STUDENT
        ));
    }
}
