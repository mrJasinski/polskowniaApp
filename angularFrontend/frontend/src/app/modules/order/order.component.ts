import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { OrderService } from "./order.service";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { OrderReadModel } from "./orderRead.model";
import { ShopService } from "../shop/shop.service";

@Component
({
  selector: 'order'
  , templateUrl: './order.component.html'
  , imports: [NgFor, DashboardNavBarComponent]
})

export class OrderComponent implements OnInit
{
    order : OrderReadModel;
    categories : Array<String>;

    actionText : string;

    constructor(private orderService : OrderService, private shopService : ShopService)
    {

    }

    ngOnInit(): void 
    {
        this.orderService.getOrderByRefNumber(this.orderService.getRefNumber()).subscribe
        (
            response => this.order = <any>response.body
        );


        this.shopService.getShopItemsCategories().subscribe
        (
            response => this.categories = <any>response.body
        );
    }

    assignActionText(category : string)
    {
        
        if (category.includes('Kurs'))
            return 'Utwórz kurs'
        else if (category.includes('Symulacja'))
            return 'Utwórz spotkanie'
        else if (category.includes('Książka'))
            return 'Nadaj przesyłkę'
        else
            return 'Przycisk do obsługi wymaganej akcji'
    }
}
