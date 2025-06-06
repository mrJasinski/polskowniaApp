package polskowniaApp.download;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
class DownloadController
{
//    very test method

    @GetMapping("/downloadFile/{filename}")
    ResponseEntity<?> downloadFile(@PathVariable String filename)
    {
        var file = new File("src/main/resources/files/" + filename);

        var resource = new FileSystemResource(file.toPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
