package polskowniaApp.fileManager;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
class FileManagerController
{
    private final FileManagerService fileService;

    FileManagerController(final FileManagerService fileService)
    {
        this.fileService = fileService;
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
