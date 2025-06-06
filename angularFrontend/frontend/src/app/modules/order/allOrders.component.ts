import { Component, OnInit } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { NgFor } from "@angular/common";
import { Order } from "./order.model";
import { OrderService } from "./order.service";
import { Router, RouterLink } from "@angular/router";

@Component
({
  selector: 'all-orders',
  templateUrl: './allOrders.component.html'
  , imports : [DashboardNavBarComponent, NgFor]
})

export class AllOrdersComponent implements OnInit
{
  orders : Array<Order>;

  constructor(private orderService : OrderService, private router : Router)
  {

  }

  ngOnInit()
  {
    this.orderService.getAllOrders().subscribe(response => this.orders = <any>response.body);
  }

  ngOrderSelected(refNumber : string)
  {
    this.orderService.setRefNumber(refNumber);

    this.router.navigate(["order/" + refNumber]);
  }
}