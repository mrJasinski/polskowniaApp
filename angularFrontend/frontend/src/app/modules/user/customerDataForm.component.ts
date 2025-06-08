import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AppConstants } from "../../constans/app.constans";
import { UserService } from "./userService.service";
import { CustomerData } from "./customerData.model.";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'customer-data-form',
  templateUrl: './customerDataForm.component.html'
  , imports: [ReactiveFormsModule, NgIf, DashboardNavBarComponent]
})

export class CustomerDataFormComponent implements OnInit
{
    customerDataForm : FormGroup;
    isInvoice = false;
    isDefault = false;

    formMsg : string;

    constructor(private router : Router, private userService : UserService)
    {
        
    }

    ngOnInit()
    {
        this.customerDataForm = new FormGroup
        ({
            'company' : new FormControl(null, this.isInvoice ? [Validators.required] : [])
            , 'taxNumber' : new FormControl(null, this.isInvoice ? [Validators.required] : [])
            , 'firstName' : new FormControl(null, [Validators.required])
            , 'lastName' : new FormControl(null, [Validators.required])
            , 'streetName' : new FormControl(null, [Validators.required])
            , 'streetNumber' : new FormControl(null, [Validators.required])
            , 'localNumber' : new FormControl(null, [Validators.required])
            , 'zipCode' : new FormControl(null, [Validators.required])
            , 'town' : new FormControl(null, [Validators.required])
            , 'phone' : new FormControl(null, [Validators.required])
            , 'email' : new FormControl(null, [Validators.required])
        });
    }

    onInvoiceSelection($event)
    {
        this.isInvoice = $event.target.checked;
        
        console.log('i ' + this.isInvoice);
    }

    onDefaultSelection($event)
    {
        this.isDefault = $event.target.checked;
    }

    onSubmit()
    {
        console.log('def ' + this.isDefault);

        var data = new CustomerData
        (
            this.isDefault
          , this.customerDataForm.get('firstName')?.value
          , this.customerDataForm.get('lastName')?.value
          , this.customerDataForm.get('streetName')?.value
          , this.customerDataForm.get('streetNumber')?.value
          , this.customerDataForm.get('zipCode')?.value
          , this.customerDataForm.get('town')?.value
          , this.customerDataForm.get('phone')?.value
          , this.customerDataForm.get('email')?.value
          , this.customerDataForm.get('localNumber')?.value   
          , this.customerDataForm.get('company')?.value   
          , this.customerDataForm.get('taxNumber')?.value   
        );

        this.userService.saveCustomerData(data).subscribe( response => 
            {
                this.formMsg = "Dane pomyślnie zapisane!"
                //this.customerDataForm.reset();
            });
    }

   
}