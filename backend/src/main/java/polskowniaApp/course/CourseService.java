package polskowniaApp.course;

import org.springframework.stereotype.Service;
import polskowniaApp.course.dto.CourseDTO;
import polskowniaApp.course.dto.CourseReadModel;
import polskowniaApp.course.dto.CourseWriteModel;
import polskowniaApp.course.lecture.Lecture;
import polskowniaApp.course.lecture.LectureReadModel;
import polskowniaApp.user.UserService;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
class CourseService
{
    private final CourseRepository courseRepo;
    private final LectureRepository lectureRepo;
    private final UserService userService;


    CourseService(final CourseRepository courseRepo, final LectureRepository lectureRepo, final UserService userService)
    {
        this.courseRepo = courseRepo;
        this.lectureRepo = lectureRepo;
        this.userService = userService;
    }

    void createCourse(final CourseWriteModel toSave)
    {
        var course = this.courseRepo.save(new Course(
                toSave.getStartDate()
                , toSave.getStartTime()
                , wrapDaysIntoString(toSave.getDays())
                , toSave.getLength()
                , toSave.getDuration()));

        System.out.println("created course " + course.getId());

        var lectures = createLecturesByCourse(course);
//jeśli obiekt course jest tylko utworzony a nie wykorzystywnay dalaej w tej metodzie tj np wyświetlany to czy warto dodawać lectures?
        //powiązanie zostało utworzone w lectures
//        course.addLectures(lectures);
//        create course object
//          create lectures in course

        var emails = toSave.getParticipants();

        if (emails !=  null)
            for (String email : emails)
                this.userService.createUserAccountByEmail(email);

//        przypisanie uczniów do kursu

        var userIds = this.userService.getUserIdsByEmails(emails);

        for (int userId : userIds)
            assignStudentToCourse(userId, course.getId());
//        create user accounts? unless exist and assign them to course
//        send mailing? about account creation and course welcome
    }

    private void assignStudentToCourse(final int userId, final int courseId)
    {
        this.courseRepo.assignStudentToCourse(userId, courseId);
    }

        // wrappery w obiekcie?

    String wrapDaysIntoString(Set<Integer> days)
    {
        var wrappedDays = new StringBuilder();

        for (int d : days)
            wrappedDays.append(d);

        return wrappedDays.toString();
    }

    List<DayOfWeek> unwrapDaysFromString(final String days)
    {
        var unwrappedDays = new ArrayList<DayOfWeek>();

        for(int i = 0; i < days.length(); i++)
        {
            var x = Integer.parseInt(String.valueOf(days.charAt(i))) + 1;
            unwrappedDays.add(DayOfWeek.of(x));
        }

        return unwrappedDays;
    }

//    List<Lecture> createLectures(final Course course)
//    {
//        var lectures = new ArrayList<Lecture>();
//
//        var day = course.getStartDate();
//
//        lectures.add(new Lecture(day, course));
//
//        var courseDays = unwrapDaysFromString(course.getDays());
//
//        for (int i = 1; i < course.getLength(); i++)
//        {
//            while(0 == 0)
//            {
//                day = day.plusDays(1);
//
//                if (courseDays.contains(day.getDayOfWeek()))
//                {
//                    lectures.add(new Lecture(day, course));
//                    break;
//                }
//            }
//        }
//
//        return lectures;
//    }

    List<Lecture> createLecturesByCourse(final Course course)
    {
//        założenia
//        mam datę startową oraz dni tygodnia w których odbywają się zajęcia
//        data startowa - pokrywa się z jednym z tych dni ->
//        TODO wyżej check czy dane są porawne
//        tworzę pierwszą lekcję na podstawie daty startowej
//        datę drugiej i kolejnych lekcji należy wyznaczyć na podstawie dni tygodnia w które odbywają się zajęcia
//        np [pon, śr] pierwsze zajęcia w pon
//        więc należy wyznaczyć datę która wypada w środę utorzyć lekcję
//        następna lekcja w poniedziałek więc data poniedziałku itd aż do uzyskania wymaganej ilości lekcji
//        iteracja do ilości - 1 ponieważ pierwsza lekcja była utworzona zawczasu

        var lectures = new ArrayList<Lecture>();

        var day = course.getStartDate();

        lectures.add(new Lecture(day, course));

        var courseDays = unwrapDaysFromString(course.getDays());

        for (int i = 1; i < course.getLength(); i++)
        {
            while(0 == 0)
            {
                day = day.plusDays(1);
                if (courseDays.contains(day.getDayOfWeek()))
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

    List<Course> getAllCourses()
    {
        return this.courseRepo.findAll();
    }

    List<CourseReadModel> getAllCoursesAsReadModel()
    {
        return getAllCourses()
                .stream()
                .map(this::toReadModel)
                .toList();
    }

    CourseDTO toDto(final Course course)
    {
//        TODO konstruktor
        return new CourseDTO(
                course.getStartDate()
                , course.getStartTime()
                , course.getDaysWithNames()
                , course.getLength()
                , course.getDuration()
        );
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

    List<CourseReadModel> getCoursesByUserIdAsReadModel(final int userId)
    {
        return getCoursesByUserId(userId)
                .stream()
                .map(this::toReadModel)
                .toList();
    }

    Course getCourseByRefNumber(final String courseRefNumber)
    {
        return this.courseRepo.findByRefNumber(courseRefNumber)
                .orElseThrow(() -> new NoSuchElementException("No course with given ref number found!"));
    }

    LectureReadModel toReadModel(final Lecture lecture)
    {
        return new LectureReadModel(
                lecture.getLectureDate()
                , lecture.getTitle()
        );
    }

    CourseReadModel toReadModel(final Course course)
    {
        return new CourseReadModel(
                course.getRefNumber()
                , course.getTitle()
                , course.getLevel()
                , course.getCategory().getName()
                , course.getStartDate()
                , course.getStartTime()
                , course.getDaysWithNames()
                , course.getLength()
                , course.getDuration()
                , course.getStatus().getName()
                , course.getLectures().stream().map(this::toReadModel).toList()
        );
    }

    CourseReadModel getCourseByRefNumberAsReadModel(final String courseRefNumber)
    {
        return toReadModel(getCourseByRefNumber(courseRefNumber));
    }
}
