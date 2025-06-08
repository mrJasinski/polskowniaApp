package polskowniaApp.calendar;

class DayData
{
    private String day;
    private String startTime;
    private String endTime;

    DayData()
    {
    }

    DayData(final String day, final String startTime, final String endTime)
    {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay()
    {
        return this.day;
    }

    public String getStartTime()
    {
        return this.startTime;
    }

    public String getEndTime()
    {
        return this.endTime;
    }
}
