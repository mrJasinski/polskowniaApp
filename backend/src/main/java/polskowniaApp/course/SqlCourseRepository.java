package polskowniaApp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCourseRepository extends CourserRepository, JpaRepository<Course, Integer>
{

}
