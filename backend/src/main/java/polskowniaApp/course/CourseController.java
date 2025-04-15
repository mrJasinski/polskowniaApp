package polskowniaApp.course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import polskowniaApp.course.dto.CourseDTO;

@RestController
class CourseController
{
    private final CourseService courseService;

    CourseController(final CourseService courseService)
    {
        this.courseService = courseService;
    }


    @PostMapping("/createCourse")
    ResponseEntity<?> createCourse(@RequestBody CourseDTO toSave)
    {
        this.courseService.createCourse(toSave);

        return ResponseEntity.ok("Course created!");
    }

    @GetMapping("/allCourses")
    ResponseEntity<?> getAllCoursesAsDto()
    {
        return ResponseEntity.ok(this.courseService.getAllAsDto());
    }

    @GetMapping("/ongoingCourses")
    ResponseEntity<?> getAllOngoingCoursesAsDto()
    {
        return ResponseEntity.ok(this.courseService.getCoursesByStatusAsDto(CourseStatus.ONGOING));
    }
}
