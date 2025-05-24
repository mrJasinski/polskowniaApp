import { Component, OnInit } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";

@Component
({
  selector: 'account-settings',
  templateUrl: './accountSettings.component.html'
  , imports: [DashboardNavBarComponent]
})

export class AccountSettingsComponent implements OnInit
{
    constructor()
    {

    }

    ngOnInit()
    {

    }
}
