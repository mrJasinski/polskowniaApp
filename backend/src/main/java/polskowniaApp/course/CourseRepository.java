package polskowniaApp.course;

import java.util.List;

interface CourseRepository
{
    List<Course> findAll();
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByUserId(int userId);

    Course save(Course toSave);
}
