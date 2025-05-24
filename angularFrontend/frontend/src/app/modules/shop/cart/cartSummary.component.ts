import { Component } from "@angular/core";
import { FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { ShopService } from "../shop.service";
import { ShopItemReadModel } from "../shopItemRead.model";
import { NgFor, NgIf } from "@angular/common";
import { CustomerDataForm } from "./customerDataForm.component";
import { Order } from "../../order/order.model";
import { CustomerData } from "../../order/customerData.model";
import { Router } from "@angular/router";

@Component
({
  selector: 'cart-summary',
  templateUrl: './cartSummary.component.html'
  , imports: [ReactiveFormsModule, NgFor, NgIf]
})

export class CartSummaryComponent 
{
  shopItems = new Array<ShopItemReadModel>;
  cartSum = 0;
  deliveryPrice = 0;
  discountValue = 0;
  checkout = 0;

  discountMsg : string;

  isInvoice = false;

  paymentMethods = ['BLIK', 'Przelew tradycyjny', 'Karta płatnicza', 'Pobranie'];

  //deliveryMethods = [
    //{"name" : 'Inpost Paczkomat', "price" : 14.99 }
    //, {"name" : 'Inpost Kurier', "price" : 19.99 }
    //, {"name" : 'DPD Kurier', "price" : 17.99 }
  //];

  deliveryMethods = [
    'Inpost Paczkomat'
    , 'Inpost Kurier'
    , 'DPD Kurier'
  ];

  orderForm : FormGroup;
  

  constructor(private shopService : ShopService, private router : Router)
  {

  }

  ngOnInit()
  {
    this.orderForm = new FormGroup(
      {
        //'invoiceSelector' : new FormControl(null)
        //, 'differentInvoiceData' : new FormControl(null)
        //, 'company' : new FormControl(null, this.isInvoice ? [Validators.required] : [])
        //, 'taxNumber' : new FormControl(null, this.isInvoice ? [Validators.required] : [])
         'firstName' : new FormControl(null, [Validators.required])
        , 'lastName' : new FormControl(null, [Validators.required])
        , 'streetName' : new FormControl(null, [Validators.required])
        , 'streetNumber' : new FormControl(null, [Validators.required])
        , 'localNumber' : new FormControl(null, [Validators.required])
        , 'zipCode' : new FormControl(null, [Validators.required])
        , 'town' : new FormControl(null, [Validators.required])
        , 'phone' : new FormControl(null, [Validators.required])
        , 'email' : new FormControl(null, [Validators.required])
        , 'paymentMethod' : new FormControl(this.paymentMethods, [Validators.required])
        , 'differentAddress' : new FormControl(null)
        , 'deliveryMethod' : new FormControl(this.deliveryMethods, [Validators.required])
        , 'comment' : new FormControl(null)
        , 'discountCodeForm' : new FormGroup(
          {
            'discountCode' : new FormControl(null)
          }
        )
        , 'isToCAccepted' : new FormControl(null, [Validators.required])
        , 'isFourteenDaysAccepted' : new FormControl(null, [Validators.required])
      });


    this.shopItems = this.shopService.getItemsAddedToCart();
    this.shopItems.forEach(item => this.cartSum += item.price);

    this.calculateCheckout();
  }

  onSubmit()
  {
      const order = new Order(
        this.isInvoice
        , new CustomerData(
          this.orderForm.get('firstName')?.value
          , this.orderForm.get('lastName')?.value
          , this.orderForm.get('streetName')?.value
          , this.orderForm.get('streetNumber')?.value
          , this.orderForm.get('zipCode')?.value
          , this.orderForm.get('town')?.value
          , this.orderForm.get('phone')?.value
          , this.orderForm.get('email')?.value
          , this.orderForm.get('localNumber')?.value
        )
        , this.orderForm.get('paymentMethod')?.value
        , this.orderForm.get('deliveryMethod')?.value
        , this.getShopItemsNumbers()
        , this.orderForm.get('discountCode')?.value
        , this.orderForm.get('isFourteenDays')?.value
        , this.orderForm.get('isToCAccepted')?.value
        , this.orderForm.get('comment')?.value
      );

      this.shopService.createOrder(order).subscribe(response => {});
  }

  getShopItemsNumbers()
  {
    const numbers = new Array<String>();

    for (let item of this.shopItems)
      numbers.push(item.refNumber)

    return numbers;
  }

  //selectInvoice($event)
  //{
    //this.isInvoice = $event.target.checked;
  //}

  applyDiscount()
  {
    this.shopService.getDiscountValue(this.orderForm.get('discountCodeForm.discountCode')?.value, this.cartSum).subscribe(response =>
    {
        this.discountValue = <number>response.body;

        this.calculateCheckout();

        this.orderForm.get('discountCodeForm.discountCode')?.reset();

        this.discountMsg = 'Kod rabatowy zastosowany!';
    }
    , error => { this.discountMsg = 'Błędny kod rabatowy!' });
  }

  calculateCheckout()
  {
    this.checkout = this.cartSum + this.deliveryPrice - this.discountValue;
  }
  
}
   