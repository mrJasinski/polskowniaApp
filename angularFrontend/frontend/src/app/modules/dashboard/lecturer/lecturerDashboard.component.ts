import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { NgIf } from "@angular/common";
import { CourseAddFormComponent } from "../../course/courseAddForm.component";

@Component
({
    selector: 'lecturer-dashboard'
    , templateUrl: './lecturerDashboard.component.html'
    , imports: [NgIf, CourseAddFormComponent]
})
export class LecturerDashboardComponent
{
    isCourseCreation = false;

    constructor(private router: Router)
    {
        
    }

    onCreateCourse()
    {
        this.isCourseCreation = true;
    }
}