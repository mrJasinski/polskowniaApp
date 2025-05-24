import { NgFor, NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";

@Component
({
  selector: 'customer-data-form',
  templateUrl: './customerDataForm.component.html'
  , imports : [ReactiveFormsModule, NgIf]
})

export class CustomerDataForm implements OnInit
{
    isInvoice = false;

    customerData : FormGroup;

    constructor()
    {

    }

    ngOnInit()
    {
        this.customerData = new FormGroup(
            {
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
}