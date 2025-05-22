import { NgFor } from "@angular/common";
import { Component } from "@angular/core";
import { RouterLink } from "@angular/router";
import { CourseReadModel } from "./courseRead.model";
import { CourseService } from "./course.service";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'all-courses',
  templateUrl: './allCourses.component.html'
  , imports : [NgFor, RouterLink, DashboardNavBarComponent]
})

export class AllCoursesComponent 
{
  courses = new Array<CourseReadModel>;

  constructor(private courseService : CourseService)
  {

  }
  
  ngOnInit() : void
  {
    this.courseService.getAllCourses().subscribe(response => {this.courses = <any>response.body;});
  }

  onClick(courseRefNumber : string)
  {
    this.courseService.courseRefNumber = courseRefNumber;
  }
}