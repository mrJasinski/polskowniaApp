package polskowniaApp.mail;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MailController
{
    private final MailService mailService;

    MailController(final MailService mailService)
    {
        this.mailService = mailService;
    }

//raczej tylko testowy

    @GetMapping("/sendMail")
    ResponseEntity<?> sendMail()
    {
        this.mailService.sendMail();


        return ResponseEntity.ok("Mail sent!");
    }

}
