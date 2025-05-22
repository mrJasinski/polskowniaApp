import { NgFor } from "@angular/common";
import { Component } from "@angular/core";
import { Student } from "./student.model";
import { StudentService } from "./student.service";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'students',
  templateUrl: './students.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class StudentsComponent 
{
    students = new Array<Student>;

    constructor(private studentService : StudentService)
    {
        
    }

    ngOnInit()
    {
// pobieranie listy kursantów z serwera
    }
}