package polskowniaApp.course;

import polskowniaApp.course.dto.CourseReadModel;

import java.util.List;
import java.util.Optional;

interface CourseRepository
{
    List<Course> findAll();
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByUserId(int userId);

    Course save(Course toSave);

    void assignStudentToCourse(int userId, int courseId);

    Optional<Course> findByRefNumber(String courseRefNumber);

    boolean existsByRefNumber(String refNumber);
}
