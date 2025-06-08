import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";

@Injectable({providedIn: 'root'})
export class CalendarService
{
    
    constructor(private http: HttpClient)
    {

    }

    getCalendarCourseDataByDate(date : string)
    {
        return this.http.get(AppConstants.APP_URL + "/calendarData", {params : {date}, observe : 'response'} );
    }
}