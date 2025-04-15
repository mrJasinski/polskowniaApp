package polskowniaApp.user;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    UserInitializer(
            final UserRepository userRepo
            , final PasswordEncoder encoder)
    {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var lecturerMail = "lecturer@example.com";

        if (this.userRepo.findByEmail(lecturerMail).isEmpty())
            this.userRepo.save(new User(
                    "Wiola"
                    , lecturerMail
                    ,this.encoder.encode("polskownia")
                    , UserRole.LECTURER
            ));

        var studentMail = "student@example.com";

        if (this.userRepo.findByEmail(studentMail).isEmpty())
            this.userRepo.save(new User(
                    "Studenciak"
                    , studentMail
                    ,this.encoder.encode("polskownia")
                    , UserRole.STUDENT
            ));
    }
}
