import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { AuthService } from "../auth/auth.service";
import { Subscription } from "rxjs";
import { LecturerDashboardComponent } from "./lecturer/lecturerDashboard.component";
import { StudentDashboardComponent } from "./student/studentDashboard.component";
import { DashboardNavBarComponent } from "./dashboardNavBar.component";
import { UserRole } from "../../constans/role.constans";

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html'
    ,imports: [NgIf, LecturerDashboardComponent, StudentDashboardComponent, DashboardNavBarComponent]
})
export class DashboardComponent implements OnInit
{
    userRole = '';
    uR = UserRole;
    private userSub = new Subscription;

    constructor(private authService: AuthService)
    {
    //     this.user = authService.loggedUser;
    }

    ngOnInit()
    {
        this.userRole = this.authService.getUserRole();
    }
}