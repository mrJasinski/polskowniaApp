package polskowniaApp.course;

import org.springframework.stereotype.Service;
import polskowniaApp.course.dto.CourseDTO;
import polskowniaApp.course.dto.CourseWriteModel;
import polskowniaApp.course.lecture.Lecture;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
class CourseService
{
    private final CourseRepository courseRepo;
    private final LectureRepository lectureRepo;


    CourseService(final CourseRepository courseRepo, final LectureRepository lectureRepo)
    {
        this.courseRepo = courseRepo;
        this.lectureRepo = lectureRepo;
    }

    void createCourse(final CourseWriteModel toSave)
    {
        var course = this.courseRepo.save(new Course(
                toSave.getStartDate()
                , toSave.getStartTime()
                , wrapDaysIntoString(toSave.getDays())
                , toSave.getLength()
                , toSave.getDuration()));

        createLecturesByCourse(course);
//        create course object
//          create lectures in course
//        create user accounts? unless exist and assign them to course
//        send mailing? about account creation and course welcome
    }

    String wrapDaysIntoString(Set<Integer> days)
    {
//        TODO temporary solution only as too sensitive

        StringBuilder wrappedDays = new StringBuilder();

        for (int d : days)
            wrappedDays.append(d);

        return wrappedDays.toString();
    }

//    metoda unwrap -> rozwinięcie do DayOfWeek

    Set<DayOfWeek> unwrapDaysFromString(final String days)
    {
        var unwrappedDays = new HashSet<DayOfWeek>();

        for(int i = 0; i < days.length(); i++)
            unwrappedDays.add(DayOfWeek.of(days.charAt(i)));

        return unwrappedDays;
    }

    Set<Lecture> createLecturesByCourse(final Course course)
    {
        var lectures = new HashSet<Lecture>();

        var day = course.getStartDate();

        lectures.add(new Lecture(day));

        for(int i = 0; i < course.getLength() - 1; i++)
        {
            while(0 == 0)
            {
                day = day.plusDays(1);

                if (unwrapDaysFromString(course.getDays()).contains(day.getDayOfWeek()))
                {
                    lectures.add(new Lecture(day, course));
                    break;
                }
            }
        }

        for (Lecture l : lectures)
            this.lectureRepo.save(l);

        return lectures;
    }

    List<Course> getAll()
    {
        return this.courseRepo.findAll();
    }

    List<CourseDTO> getAllAsDto()
    {
        return getAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    CourseDTO toDto(final Course course)
    {
//        TODO konstruktor
        return new CourseDTO();
    }

    List<Course> getCoursesByStatus(CourseStatus status)
    {
        return this.courseRepo.findByStatus(status);
    }

    List<CourseDTO> getCoursesByStatusAsDto(CourseStatus status)
    {
        return getCoursesByStatus(status)
                .stream()
                .map(this::toDto)
                .toList();
    }

    List<Course> getCoursesByUserId(final int userId)
    {
        return this.courseRepo.findByUserId(userId);
    }

    List<CourseDTO> getCoursesByUserIdAsDto(final int userId)
    {
        return getCoursesByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }
}
