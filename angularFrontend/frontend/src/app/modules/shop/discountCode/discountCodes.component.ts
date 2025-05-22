import { Component } from "@angular/core";
import { ShopService } from "../shop.service";
import { DiscountCode } from "./discountCode.model";
import { NgFor, NgIf } from "@angular/common";
import { FormsModule, NgForm } from "@angular/forms";
import { MatDatepicker, MatDatepickerModule } from "@angular/material/datepicker";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { DateAdapter, MAT_DATE_LOCALE } from "@angular/material/core";
import { CustomDateAdapter, } from "../../../constans/customDateAdapter";

@Component
({
  selector: 'discount-codes',
  templateUrl: './discountCodes.component.html'
  , providers : [{provide : DateAdapter, useClass : CustomDateAdapter }]
  , imports : [NgFor, NgIf, FormsModule, MatDatepickerModule, MatFormFieldModule, MatInputModule]
})

export class DiscountCodesComponent 
{
    codes = new Array<DiscountCode>;

    startDate : Date;  
    endDate = new Date();

    responseStatus = 0;

    constructor(private shopService : ShopService, private dateAdapter: DateAdapter<Date>)
    {
        this.dateAdapter.setLocale('pl-PL');

    }

    ngOnInit()
    {
        this.shopService.getDiscountCodes().subscribe(response => 
        {
          this.codes = <any>response.body;
        });
    }

    onSubmit(form : NgForm)
    {
        const code = new DiscountCode
        (
            form.value.code
            , form.value.value
            , form.value.type
            , this.startDate
            , this.endDate
        );

        this.shopService.generateDiscountCode(code).subscribe(resData => {this.responseStatus = resData.status });
        
    }

    onStartDateChange($event)
    {
        this.startDate = $event.target.value;

        this.startDate = this.convertDateToLocal(this.startDate);
    }

    onEndDateChange($event)
    {
        this.endDate = $event.target.value;

        this.endDate = this.convertDateToLocal(this.endDate);
    }

    convertDateToLocal(d : Date)
    {
        // ponieważ system konwertuje datę na GMT i przesuwa ją na dzień wcześniejszy to jest kompensowana różnica strefy czasowej

        return new Date(d.getTime() - (d.getTimezoneOffset() * (60000)));

    }
}