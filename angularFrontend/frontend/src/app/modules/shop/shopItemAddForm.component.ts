import { Component, ViewChild } from "@angular/core";
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { ShopService } from "./shop.service";
import { NgFor } from "@angular/common";
import { ShopItem } from "./shopItem.model";


@Component
({
  selector: 'shop-item-add-form',
  templateUrl: './shopItemAddForm.component.html'
  , imports : [FormsModule, NgFor]
})

export class ShopItemAddFormComponent 
{
    categories = new Array<String>;
    levels = ['A0', 'A1', 'A2', 'B1', 'B2', 'C1', 'C2'];
    category = '';
    level = '';
    item : ShopItem;
    @ViewChild('shopItemAddForm') shopItemAddForm : NgForm;
    isUpdate = false;

    constructor(private shopService : ShopService)
    {

    }

    ngOnInit()
    {
        this.shopService.getShopItemsCategories().subscribe(response => 
        {
          this.categories = <any>response.body;
          this.categories.splice(0, 1);
        });

        const refNumber = this.shopService.getItemRefNumber();

        if ( refNumber != '')
            {
                this.isUpdate = true;

                this.shopService.getShopItemByRefNumber(refNumber).subscribe((response) => { this.item = <any>response.body 

                    this.shopItemAddForm.form.patchValue
                    ({
                        title : this.item.title
                        , price : this.item.price
                        , description : this.item.description
                        , category : this.item.category
                        , length : this.item.length
                        , duration : this.item.duration
                        , level : this.item.level
                    });
                }); 
            }        
    }

    onSubmit(form : NgForm)
    {
        const item = new ShopItem(
            form.value.title
            , form.value.price
            , form.value.description
            , this.category
            , form.value.length
            , form.value.duration
            , this.level
        );

        this.shopService.sendShopItemAddForm(item).subscribe(resData => {});

        form.reset();

        //napis pod opróżnionym formularzem jako inforamcja o skutecznym dodaniu pozycji?
    }

    onCategorySelection($event)
    {
        this.category = $event.target.value;
    }

    onLevelSelection($event)
    {
        this.level = $event.target.value;
    }
}