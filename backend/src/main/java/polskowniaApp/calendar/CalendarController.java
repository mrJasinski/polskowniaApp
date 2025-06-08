package polskowniaApp.calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CalendarController
{
    private final CalendarService calendarService;

    CalendarController(final CalendarService calendarService)
    {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendarData")
    ResponseEntity<?> getCalendarDataByDate(@RequestParam("date") String date)
    {
        return ResponseEntity.ok(this.calendarService.getCalendarDataByDate(date));
    }
}
