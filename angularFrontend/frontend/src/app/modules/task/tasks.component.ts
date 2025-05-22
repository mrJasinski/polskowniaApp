import { Component } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'tasks',
  templateUrl: './tasks.component.html',
  imports: [DashboardNavBarComponent]
})

export class TasksComponent 
{
}