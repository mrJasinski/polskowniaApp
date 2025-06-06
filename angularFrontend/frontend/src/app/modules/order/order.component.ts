import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { OrderService } from "./order.service";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { OrderReadModel } from "./orderRead.model";

@Component
({
  selector: 'order'
  , templateUrl: './order.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class OrderComponent implements OnInit
{
    order : OrderReadModel;

    constructor(private orderService : OrderService)
    {

    }

    ngOnInit(): void 
    {
        this.orderService.getOrderByRefNumber(this.orderService.getRefNumber()).subscribe
        (
            response => this.order = <any>response.body
        );
    }
}
