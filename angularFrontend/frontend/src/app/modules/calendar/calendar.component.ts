import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { weekdays } from "../../constans/weekdays.constant";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { CalendarService } from "./calendar.service";
import { CalendarData } from "./calendarData.model";

@Component
({
  selector: 'calendar',
  templateUrl: './calendar.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class CalendarComponent implements OnInit
{
    weekdays = weekdays;

    hours = new Array<String>;

    calendarData : CalendarData;
    weekStart : Date;
    weekEnd : Date;

    day = new Date();

    constructor(private calendarService : CalendarService)
    {

    }

    ngOnInit()
    {
        //parameters are 
        // hour when day starts
        // hour when day ends
        this.createHoursArray(7, 22);

        this.getCourseDataByDate(new Date());
    }

    createHoursArray(startHour : number, endHour : number)
    {
        for (let i = startHour; i < endHour; i++)
            for (let j = 0; j < 4; j++)
                this.hours.push(`${i}:${j === 0 ? `00` : 15*j}`);
    }

    onCellSelection(i : number, j : number)
    {
        // i stands for row index from array
        // j stands for column index from array
        // both have to be ++ as table structure is title_col/row then iterating by array

        console.log('cell ' + i + " " + j );

        var table = document.getElementById('calendarTable') as HTMLTableElement;

        var cell = table.rows[i + 1].cells[j + 1];
        
        cell.textContent = 'yolo!';
        cell.style.backgroundColor = 'blueviolet';
    }

    onSwitchWeek(d : number)
    {
        // d is -7 or 7 only as it's stands fro one week change prpevious or next

        this.day.setDate(this.day.getDate() + d);

        this.getCourseDataByDate(this.day);
    }

    getCourseDataByDate(date : Date)
    {
        // obcięcię końców czasu (wraz literą T) aby spring mógł przetworzyć datę na LocalDate
        var convertedDate = date.toISOString().substring(0, 10);

        this.calendarService.getCalendarCourseDataByDate(convertedDate).subscribe(response => 
            {
                this.calendarData = <any>response.body

                this.weekStart = this.calendarData.weekStart;
                this.weekEnd = this.calendarData.weekEnd;

                this.fillCalendar(this.calendarData);
            });
    }

    fillCalendar(calendarData : CalendarData)
    {
        var table = document.getElementById('calendarTable') as HTMLTableElement;
        var cell : HTMLTableCellElement;
        
        for (let d of calendarData.days)
        {
            var j = this.weekdays.indexOf(d.day) + 1;

            for (let i = this.hours.indexOf(d.startTime); i < this.hours.indexOf(d.endTime); i++)
            {
                cell = table.rows[i + 1].cells[j];

                cell.textContent = 'yolo!';
                cell.style.backgroundColor = 'blueviolet';
            }
        }
        
    }
}