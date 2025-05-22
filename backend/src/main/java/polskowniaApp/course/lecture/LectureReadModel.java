package polskowniaApp.course.lecture;

import java.time.LocalDate;

public class LectureReadModel
{
    private LocalDate lectureDate;
    private String title;

    public LectureReadModel()
    {
    }

    public LectureReadModel(final LocalDate lectureDate, final String title)
    {
        this.lectureDate = lectureDate;
        this.title = title;
    }

    public LocalDate getLectureDate()
    {
        return this.lectureDate;
    }

    public String getTitle()
    {
        return this.title;
    }
}
