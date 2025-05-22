package polskowniaApp.course;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polskowniaApp.course.dto.CourseWriteModel;
import polskowniaApp.security.JwtService;

@RestController
class CourseController
{
    private final CourseService courseService;
    private final JwtService jwtService;

    CourseController(final CourseService courseService, final JwtService jwtService)
    {
        this.courseService = courseService;
        this.jwtService = jwtService;
    }


    @PostMapping("/createCourse")
    ResponseEntity<?> createCourse(@RequestBody CourseWriteModel toSave)
    {
        this.courseService.createCourse(toSave);

        return ResponseEntity.ok("Course created!");
    }

    @GetMapping("/allCourses")
    ResponseEntity<?> getAllCoursesAsReadModel()
    {
        return ResponseEntity.ok(this.courseService.getAllCoursesAsReadModel());
    }

    @GetMapping("/ongoingCourses")
    ResponseEntity<?> getAllOngoingCoursesAsDto()
    {
        return ResponseEntity.ok(this.courseService.getCoursesByStatusAsDto(CourseStatus.ONGOING));
    }

    @GetMapping("/myCourses")
    ResponseEntity<?> getMyCourses(HttpServletRequest request)
    {
        return ResponseEntity.ok(this.courseService.getCoursesByUserIdAsReadModel(this.jwtService.getUserIdFromToken(request)));
    }

    @GetMapping("/myCourses/{courseRefNumber}")
    ResponseEntity<?> getCourseReadModelByRefNumber(@PathVariable String courseRefNumber, HttpServletRequest request)
    {
        return ResponseEntity.ok(this.courseService.getCourseByRefNumberAsReadModel(courseRefNumber));
    }
}
