import { Component, OnInit } from "@angular/core";
import { Course } from "./course.model";
import { CourseReadModel } from "./courseRead.model";
import { NgFor } from "@angular/common";
import { CourseService } from "./course.service";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'course',
  templateUrl: './course.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class CourseComponent implements OnInit
{
  course : CourseReadModel;

  constructor(private courseService : CourseService)
  {

  }

  ngOnInit() : void
  {
    this.courseService.getCourseByRefNumber(this.courseService.courseRefNumber).subscribe((response) => { this.course = <any>response.body });
  }
}
