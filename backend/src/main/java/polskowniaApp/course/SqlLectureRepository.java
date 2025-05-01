package polskowniaApp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import polskowniaApp.course.lecture.Lecture;

import java.util.List;
import java.util.Set;

@Repository
interface SqlLectureRepository extends LectureRepository, JpaRepository<Lecture, Integer>
{

}
