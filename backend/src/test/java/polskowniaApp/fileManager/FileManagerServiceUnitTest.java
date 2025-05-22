package polskowniaApp.fileManager;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static polskowniaApp.fileManager.FileManagerService.STORAGE_DIRECTORY;

class FileManagerServiceUnitTest
{
//    void saveFile(MultipartFile toSave)
//    {
////        TODO
////        w ten sposób zaciąga nazwę nadaną przez użytkownika
////        jest to ok w przypadku plików nauczyciela gdzie nazwa jest celowa
////        w przypadku obsługi plikow od uczniów (prace domowe) etc należy zmienić nazwe na powiązaną z kontem aby było łatwo je rozpoznać
//
//        if (toSave == null)
//            throw new NullPointerException("Provided file is null!");
//
//        var targetFile = new File(STORAGE_DIRECTORY + File.separator + toSave.getOriginalFilename());
//
//        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY))
//            throw new SecurityException("Unsupported file name!");
//
//        try
//        {
//            Files.copy(toSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e)
//        {
//            throw new RuntimeException(e);
//        }
//
//    }


}