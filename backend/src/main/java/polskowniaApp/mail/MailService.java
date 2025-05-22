package polskowniaApp.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import polskowniaApp.user.dto.UserDTO;
import polskowniaApp.utils.Utils;

@Service
public class MailService
{
    private final JavaMailSender mailSender;

    MailService(final JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String mailTo, String subject, String text)
    {
        var message = new SimpleMailMessage();
        message.setFrom(Utils.APP_MAIL);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);

        this.mailSender.send(message);
    }

    public void sendRegistrationMail(final String  email, final String password)
    {
        var subject = "Rejestracja w serwisie Polskownia!";
        var text = String.format("""
                Dzień dobry!
                
                Konto w serwisie Polskownia zostało utworzone! Życzymy owocnej nauki!
                Dane logowania to:
                login: %s
                hasło: %s
                
                podczas pierwszej wizyty w systemie należy zmienić automatycznie wygenerowane hasło na własne!
                
                Pozdrawiamy
                Zespół Polskownia
                """, email, password);

        sendSimpleMessage(email, subject, text);
    }

    void sendMail()
    {
        var mail = "wdorosz0411@gmail.com";
        var subject = "Mail testowy!";

        var text = """
                To jest mail testowy systemu strony internetowej Żoneczki!
                """;

        sendSimpleMessage(mail, subject, text);
    }

    public void sendPasswordResetMail(final String email, final String newPass)
    {
        var subject = "Rejestracja w serwisie Polskownia!";
        var text = String.format("""
                Dzień dobry!
                
                W związku z zapomnianym hasłem przesyłamy niżej nowe dane logowania (e mail pozostał bez zmian).
                Aktualne dane logowania to:
                
                login: %s
                hasło: %s
                
                podczas najbliższej wizyty w systemie należy zmienić automatycznie wygenerowane hasło na własne!
                
                Pozdrawiamy
                Zespół Polskownia
                """, email, newPass);

        sendSimpleMessage(email, subject, text);

    }
}
