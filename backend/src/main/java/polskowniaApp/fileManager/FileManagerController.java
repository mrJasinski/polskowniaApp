package polskowniaApp.fileManager;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import polskowniaApp.security.JwtService;
import polskowniaApp.user.UserService;

@RestController
class FileManagerController
{
    private final FileManagerService fileService;
    private final UserService userService;
    private final JwtService jwtService;

    FileManagerController(final FileManagerService fileService, final UserService userService, final JwtService jwtService)
    {
        this.fileService = fileService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadFile(@RequestParam("uploadedFile")MultipartFile uploadedFile)
    {
        this.fileService.saveFile(uploadedFile);

        return ResponseEntity.ok("File uploaded!");
    }

//    very test method

    @GetMapping("/getFiles")
    ResponseEntity<?> getFiles()
    {
        return ResponseEntity.ok(this.fileService.getFilesAsWrapper());
    }


}
