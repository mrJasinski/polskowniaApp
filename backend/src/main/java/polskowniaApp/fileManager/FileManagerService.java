package polskowniaApp.fileManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileManagerService
{
    public static final String STORAGE_DIRECTORY = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "files";

    public void saveFile(MultipartFile toSave)
    {
//        TODO
//        w ten sposób zaciąga nazwę nadaną przez użytkownika
//        jest to ok w przypadku plików nauczyciela gdzie nazwa jest celowa
//        w przypadku obsługi plikow od uczniów (prace domowe) etc należy zmienić nazwe na powiązaną z kontem aby było łatwo je rozpoznać

        if (toSave == null)
            throw new NullPointerException("Provided file is null!");

        var targetFile = new File(STORAGE_DIRECTORY + File.separator + toSave.getOriginalFilename());

        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY))
            throw new SecurityException("Unsupported file name!");

        try
        {
            Files.copy(toSave.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    List<FileWrapper> getFilesAsWrapper()
    {
        var result = new ArrayList<FileWrapper>();

        var files = new File(STORAGE_DIRECTORY).listFiles();

        if (files == null)
            throw new NullPointerException("No files found!");

        for (File f : files)
            result.add(new FileWrapper(f.getName(), FilenameUtils.getExtension(f.getName()), getReadableFileSize(FileUtils.sizeOf(f))));

        return result;
    }

    String getReadableFileSize(final long size)
    {
        return FileUtils.byteCountToDisplaySize(size);
    }
}
