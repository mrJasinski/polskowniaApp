package polskowniaApp.course;

import org.springframework.stereotype.Service;
import polskowniaApp.course.dto.CourseDTO;
import polskowniaApp.course.lecture.Lecture;

import java.time.DayOfWeek;
import java.time.LocalDate;
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


    void createCourse(final CourseDTO toSave)
    {
//        create course object
//          create lectures in course
//        create user accounts? if doesn't exist
//        send mailing? about account creation and course welcome
    }

    Set<Lecture> createLecturesByCourse(final CourseDTO course)
    {
//        creating lectures by course data
//        -start date
//        -days with lectures
//        -amount of lectures

//        pierwsza lekcja tworzy się na podstawie daty startowej
//        następne - dzień tygodnia daty startowej przejście do następnego dnia zajęć i ustalenie daty
//        np zajęcia poniedziałek środa (poniedziałek jako pierwszy dzień) ustalenie daty ze środy

        var lectures = new HashSet<Lecture>();

        var day = course.getStartDate();

        lectures.add(new Lecture(day));

        for(int i = 0; i < course.getLength() - 1; i++)
        {
            while(0 == 0)
            {
                day = day.plusDays(1);

//                TODO konwersja
                if (course.getDays().contains(day.getDayOfWeek()))
                {
                    lectures.add(new Lecture(day));
                    break;
                }
            }



//            do daty startowej dodaje się dzień i sprawdza czy taki dzień tygodnia występuje w zestawie dni kursu
//            jeśli tak to odzyskiwana jest data i na jej podstawie tworzona lekcja
//            po czym następuje przejście do następnej iteracji i tak aby
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
