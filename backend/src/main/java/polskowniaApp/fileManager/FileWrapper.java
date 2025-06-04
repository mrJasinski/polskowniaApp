package polskowniaApp.fileManager;

public class FileWrapper
{
    private String name;
    private String format;
    private String size;

    FileWrapper()
    {
    }

    public FileWrapper(final String name)
    {
        this.name = name;
    }

    FileWrapper(final String name, final String format, final String size)
    {
        this.name = name;
        this.format = format;
        this.size = size;
    }

    public String getName()
    {
        return this.name;
    }

    public String getFormat()
    {
        return this.format;
    }

    public String getSize()
    {
        return this.size;
    }
}
