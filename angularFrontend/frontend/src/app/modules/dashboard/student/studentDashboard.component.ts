import { Component, OnInit } from "@angular/core";
import { Subscription } from "rxjs";
import { AuthService } from "../../auth/auth.service";

@Component({
    selector: 'student-dashboard',
    templateUrl: './studentDashboard.component.html'
})
export class StudentDashboardComponent implements OnInit
{
    userRole = '';
    private userSub = new Subscription;

    constructor(private authService: AuthService)
    {

    }

    ngOnInit()
    {
        this.userSub = this.authService.user.subscribe(user =>
        {
            if(user != null)
                this.userRole = user.role;
        });
    }
}