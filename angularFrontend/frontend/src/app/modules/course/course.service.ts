import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";
import { Course } from "./course.model";
import { NgForm } from "@angular/forms";

@Injectable({providedIn: 'root'})
export class CourseService
{
    courseRefNumber : string;

    constructor(private http: HttpClient)
    {

    }

    sendCreateCourseForm(course: Course)
    {
        return this.http.post<Course>(AppConstants.APP_URL + AppConstants.ADD_COURSE_FORM_URL, 
            {
               startDate : course.startDate
               , startTime : course.startTime
               , days : course.days
               , length : course.length
               , duration : course.duration
               , participants : course.participants
            });
    }

    getMyCourses()
    {
        return this.http.get(AppConstants.APP_URL + AppConstants.MY_COURSES_URL, {observe : 'response'});
    }

    getCourseByRefNumber(courseRefNumber: string) 
    {
        return this.http.get(AppConstants.APP_URL + AppConstants.MY_COURSES_URL + "/" + courseRefNumber, { observe : 'response'});
    }

    getAllCourses() 
    {
        return this.http.get(AppConstants.APP_URL + AppConstants.ALL_COURSES_URL, {observe : 'response'});
    }
}