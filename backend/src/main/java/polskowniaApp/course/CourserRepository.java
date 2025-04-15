package polskowniaApp.course;

import java.util.List;

interface CourserRepository
{
    List<Course> findAll();

    List<Course> findByStatus(CourseStatus status);
}
