import { NgFor } from "@angular/common";
import { Component } from "@angular/core";
import { ShopItem } from "../shopItem.model";
import { ShopService } from "../shop.service";
import { RouterLink } from "@angular/router";
import { ShopItemReadModel } from "../shopItemRead.model";

@Component
({
  selector: 'cart',
  templateUrl: './cart.component.html'
  , imports: [NgFor, RouterLink]
})

export class CartComponent 
{
    shopItems = new Array<ShopItemReadModel>;
    sum = 0;

    constructor(private shopService: ShopService)
    {
    }
    
    ngOnInit()
    {
        this.shopItems = this.shopService.getItemsAddedToCart();
        this.shopItems.forEach(item => this.sum += item.price)
    }

    
    onDeleteFromCart(item : ShopItemReadModel)
    {
        const i = this.shopItems.indexOf(item);
        this.shopItems.splice(i, 1);

        this.sum = this.sum - item.price;
    }
}
