import { NgFor } from "@angular/common";
import { Component } from "@angular/core";
import { Student } from "./student.model";
import { DashboardNavBarComponent } from "../../dashboard/dashboardNavBar.component";
import { UserService } from "../userService.service";

@Component
({
  selector: 'students',
  templateUrl: './students.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class StudentsComponent 
{
    students = new Array<Student>;

    constructor(private userService : UserService)
    {
        
    }

    ngOnInit()
    {
      this.userService.getStudents().subscribe(response => { this.students = <any>response.body });
    }
}