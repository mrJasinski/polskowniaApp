package polskowniaApp.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import polskowniaApp.user.dto.UserDTO;
import polskowniaApp.utils.Utils;

@Service
class MailService
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

    public void sendRegistrationMail(UserDTO user)
    {
        var mail = user.getEmail();
        var subject = "Dziękujemy za rejestrację w serwisie!";
        var text = String.format("""
                Dzień dobry %s!
                
                Dziękujemy za rejestrację w serwisie Polskownia! Życzymy przyjemnej nauki!
                
                Pozdrawiamy
                Zespół Polskownia
                """, user.getName());

        sendSimpleMessage(mail, subject, text);
    }
}
