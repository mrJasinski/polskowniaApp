import { DayData } from "./dayData.model";

export class CalendarData
{
    constructor
    (
        public weekStart : Date
        , public weekEnd : Date
        , public days : Array<DayData>
    )
    {

    }
}