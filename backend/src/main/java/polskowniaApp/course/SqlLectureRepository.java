package polskowniaApp.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polskowniaApp.course.lecture.Lecture;

@Repository
interface SqlLectureRepository extends LectureRepository, JpaRepository<Lecture, Integer>
{
}
