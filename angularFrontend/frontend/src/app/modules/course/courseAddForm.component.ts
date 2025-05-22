import { Component } from "@angular/core";
import { FormsModule, NgForm } from "@angular/forms";
import { CourseService } from "./course.service";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatTimepickerModule} from '@angular/material/timepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from "@angular/material/input";
import { DateAdapter} from "@angular/material/core";
import {MatCheckboxModule} from '@angular/material/checkbox';
import { DatePipe, NgFor } from "@angular/common";
import { Course } from "./course.model";

@Component
({
    selector: 'course-add-form'
    , templateUrl: './courseAddForm.component.html'
    , imports: [FormsModule, MatDatepickerModule, MatTimepickerModule, MatFormFieldModule, MatInputModule, MatCheckboxModule, NgFor]
})

export class CourseAddFormComponent
{
    weekdays = ['Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota', 'Niedziela'];
    durationTimes = [30, 45, 60, 90];
    duration = 0;
    days = new Array<number>;
    startDate = new Date();   
    participants = new Array<String>;
    startTime = new Date();

    constructor(private courseService: CourseService, private dateAdapter: DateAdapter<Date>)
    {
        this.dateAdapter.setLocale('pl-PL');
    }

    onSubmit(form: NgForm)
    {
        this.startTime = form.value.timePicker;
        const time = this.startTime.toLocaleTimeString();

        const course = new Course(
            // form.value.datePicker
             this.startDate
            , time
            , this.days
            , form.value.length
            , this.duration
            , this.createParticipantsArray(form.value.participants)
        );

        this.courseService.sendCreateCourseForm(course).subscribe(resData =>{});
        //tu raczej obiekt z form jako że trzeba też uwzględnić listę dni generowaną "na miejscu" + dojdzie jeszcze obsługa maili też pewnie jako konwersja na tablicę 
        // lub przekazanie do backendu stringa i tam jego obróbka aby uzyskać maile
        // this.courseService.sendCreateCourseForm(form);
    }

    createParticipantsArray(toTrim : string)
    {        
        return this.participants = toTrim.split("\n");
    }

    onSelectDuration($event)
    {
        this.duration = $event.target.value;

        console.log(this.duration);
    }

    onDateChange($event)
    {
        this.startDate = $event.target.value;
    }

    onSelection($event)
    {
        const day = $event.target.value;
        const isChecked = $event.target.checked;

        const index = this.weekdays.indexOf(day);

        // zaznaczenie checkboxa dodaje jego index z tablicy weekdays który potem może być przetłumaczony w backenddzie na DayOfWeek

         if (isChecked && !this.days.includes(day))
             this.days.push(index);

         if(!isChecked && this.days.includes(day))
         {
             const i = this.days.indexOf(index);
             this.days.splice(i, 1);
         }

        console.log(this.days);
    }

}