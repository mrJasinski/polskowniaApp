import { Component } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'orders',
  templateUrl: './orders.component.html'
  , imports : [DashboardNavBarComponent]
})

export class OrdersComponent 
{
}