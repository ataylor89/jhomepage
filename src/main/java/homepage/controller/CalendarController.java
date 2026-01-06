package homepage.controller;

import homepage.service.CalendarService;
import homepage.model.calendar.Calendar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/data")
    public Calendar retrieveCalendarGet(int year) {
        return calendarService.getCalendar(year);
    }

    @PostMapping("/data")
    public Calendar retrieveCalendarPost(int year) {
        return calendarService.getCalendar(year);
    }

}
