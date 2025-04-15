package polskowniaApp.course;

import org.springframework.stereotype.Service;
import polskowniaApp.course.dto.CourseDTO;

import java.util.List;

@Service
class CourseService
{
    private final CourserRepository courseRepo;


    CourseService(final CourserRepository courseRepo)
    {
        this.courseRepo = courseRepo;
    }


    void createCourse(final CourseDTO toSave)
    {
//        create course object
//          create lectures in course
//        create user accounts? if doesn't exist
//        send mailing? about account creation and course welcome
    }

    void createLecturesByCourse(final CourseDTO course)
    {
//        creating lectures by course data
//        -start date
//        -days with lectures
//        -amount of lectures

//        pierwsza lekcja tworzy się na podstawie daty startowej
//        następne - dzień tygodnia daty startowej przejście do następnego dnia zajęć i ustalenie daty
//        np zajęcia poniedziałek środa (poniedziałek jako pierwszy dzień) ustalenie daty ze środy

        for(int i = 0; i < course.getLength() - 1; i++)
        {

        }
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
}
