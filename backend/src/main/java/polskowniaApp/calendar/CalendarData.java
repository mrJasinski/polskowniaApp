package polskowniaApp.calendar;

import java.time.LocalDate;
import java.util.List;

class CalendarData
{
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private List<DayData> days;

    CalendarData()
    {
    }

    CalendarData(final LocalDate weekStart, final LocalDate weekEnd, final List<DayData> days)
    {
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.days = days;
    }

    public LocalDate getWeekStart()
    {
        return this.weekStart;
    }

    public LocalDate getWeekEnd()
    {
        return this.weekEnd;
    }

    public List<DayData> getDays()
    {
        return this.days;
    }
}
