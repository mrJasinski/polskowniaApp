import { Component, OnInit } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { Router } from "@angular/router";
import { AppConstants } from "../../constans/app.constans";
import { UserService } from "./userService.service";
import { CustomerData } from "./customerData.model.";
import { NgFor } from "@angular/common";

@Component
({
  selector: 'account-settings',
  templateUrl: './accountSettings.component.html'
  , imports: [DashboardNavBarComponent, NgFor]
})

export class AccountSettingsComponent implements OnInit
{
  dataList = new Array<CustomerData>;

    constructor(private router : Router, private userService : UserService)
    {

    }

    ngOnInit()
    {
      this.userService.getCustomerDataList().subscribe( response => { this.dataList = <any>response.body });
    }

     onAddressForm()
    {
        this.router.navigate([AppConstants.ADDRESS_FORM_URL]);
    }
}
