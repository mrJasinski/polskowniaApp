import { Component, OnInit } from "@angular/core";
import { ShopService } from "./shop.service";
import { ShopItem } from "./shopItem.model";

@Component
({
  selector: 'shop-item',
  templateUrl: './shopItem.component.html'
  , imports : []
})

export class ShopItemComponent implements OnInit
{
  item : ShopItem;

  constructor(private shopService : ShopService)
  {

  }

    ngOnInit()
    {
        this.shopService.getShopItemByRefNumber(this.shopService.getItemRefNumber()).subscribe( response => this.item = <any>response.body);
    }
}