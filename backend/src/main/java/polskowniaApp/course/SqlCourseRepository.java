package polskowniaApp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
interface SqlCourseRepository extends CourseRepository, JpaRepository<Course, Integer>
{
    @Override
    @Query(value = "SELECT courses.* " +
            "FROM courses " +
            "JOIN course_assignments ON course_assignments.course_id = courses.id  " +
            "WHERE :userId = course_assignments.user_id", nativeQuery = true)
    List<Course> findByUserId(int userId);

    @Override
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO course_assignments (course_id, user_id) VALUES (:courseId, :userId)", nativeQuery = true)
    void assignStudentToCourse(int userId, int courseId);
}
