import { Component, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";
import { UserRole } from "../../constans/role.constans";
import { AuthService } from "../auth/auth.service";
import { NgIf } from "@angular/common";

@Component({
    selector: 'dashboard-nav-bar',
    templateUrl: './dashboardNavBar.component.html'
    , imports : [RouterLink, NgIf]
})
export class DashboardNavBarComponent implements OnInit
{
    userRole = '';
    uR = UserRole;

    constructor(private authService : AuthService)
    {

    }

    ngOnInit()
    {
        this.userRole = this.authService.getUserRole();
    }
}