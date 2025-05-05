package polskowniaApp.course;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
class CourseInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final CourseRepository courseRepo;
    private final CourseService courseService;

    CourseInitializer(final CourseRepository courseRepo, final CourseService courseService)
    {
        this.courseRepo = courseRepo;
        this.courseService = courseService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var refNumber = "CI_B2_202555100";

        Course course1 = null;

        if (!this.courseRepo.existsByRefNumber(refNumber))
            course1 = this.courseRepo.save(new Course(
                    "Kurs indywidualny przygotowujący do egzaminu"
                    , Level.B2
                    , Category.COURSE_INDIVIDUAL
                    , LocalDate.of(2025, 5, 5)
                    , LocalTime.of(10, 0)
                    , "02"
                    , 10
                    , 90
            ));



        if (course1 != null)
        {
            this.courseService.createLecturesByCourse(course1);

            this.courseRepo.assignStudentToCourse(3, course1.getId());
        }

    }
}
