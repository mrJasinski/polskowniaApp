import { Component, OnInit } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { Order } from "./order.model";
import { OrderService } from "./order.service";
import { NgFor } from "@angular/common";

@Component
({
  selector: 'orders',
  templateUrl: './orders.component.html'
  , imports : [DashboardNavBarComponent, NgFor]
})

export class OrdersComponent implements OnInit
{
  orders : Array<Order>;

  constructor(private orderService : OrderService)
  {

  }

  ngOnInit()
  {
    this.orderService.getOrders().subscribe(response => this.orders = <any>response.body);
  }
}