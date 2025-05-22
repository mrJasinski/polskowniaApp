import { Component } from "@angular/core";
import { FormsModule, NgForm } from "@angular/forms";
import { ShopService } from "../shop.service";
import { ShopItemReadModel } from "../shopItemRead.model";
import { NgFor, NgIf } from "@angular/common";

@Component
({
  selector: 'cart-summary',
  templateUrl: './cartSummary.component.html'
  , imports : [FormsModule, NgFor, NgIf]
})

export class CartSummaryComponent 
{
  shopItems = new Array<ShopItemReadModel>;
  cartSum = 0;
  deliveryPrice = 0;
  discountValue = 0;
  checkout = 0;

  isInvoice = false;

  paymentMethods = ['BLIK', 'Przelew tradycyjny', 'Karta płatnicza', 'Pobranie'];

  deliveryMethods = [
    {"name" : 'Inpost Paczkomat', "price" : 14.99 }
    , {"name" : 'Inpost Kurier', "price" : 19.99 }
    , {"name" : 'DPD Kurier', "price" : 17.99 }
  ];
  

  constructor(private shopService : ShopService)
  {

  }

  ngOnInit()
  {
    this.shopItems = this.shopService.getItemsAddedToCart();
    this.shopItems.forEach(item => this.cartSum += item.price);

    this.calculateCheckout();
  }

  onSubmit(form : NgForm)
  {

  }

  selectInvoice($event)
  {
    this.isInvoice = $event.target.checked;
  }

  applyDiscount(discount : NgForm)
  {
    this.shopService.getDiscountValue(discount.value.discountCode, this.cartSum).subscribe(response =>
    {
        this.discountValue = <number>response.body;

        this.calculateCheckout();
    });
  }

  calculateCheckout()
  {
    this.checkout = this.cartSum + this.deliveryPrice - this.discountValue;
  }
  
}
   