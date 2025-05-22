import { Component } from "@angular/core";
import { CourseService } from "../course.service";
import { Course } from "../course.model";
import { NgFor } from "@angular/common";
import { CourseReadModel } from "../courseRead.model";
import { RouterLink } from "@angular/router";
import { DashboardNavBarComponent } from "../../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'my-courses',
  templateUrl: './myCourses.component.html'
  , imports : [NgFor, RouterLink, DashboardNavBarComponent]
})

export class MyCoursesComponent 
{
  myCourses = new Array<CourseReadModel>;

  constructor(private courseService : CourseService)
  {

  }
  
  ngOnInit() : void
  {
    this.courseService.getMyCourses().subscribe(response => {this.myCourses = <any>response.body;});
  }

  onClick(courseRefNumber : string)
  {
    this.courseService.courseRefNumber = courseRefNumber;
  }
}