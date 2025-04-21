package polskowniaApp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlCourseRepository extends CourseRepository, JpaRepository<Course, Integer>
{
    @Override
    @Query(value = "SELECT * " +
            "FROM courses " +
            "JOIN course_assignments ON course_assignments.course_id = courses.id  " +
            "WHERE :userId = course_assignments.user_id", nativeQuery = true)
    List<Course> findByUserId(int userId);
}
