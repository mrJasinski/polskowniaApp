import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";
import { AuthService } from "../modules/auth/auth.service";
import { Subscription } from "rxjs";
import { UserRole } from "../constans/role.constans";

@Component
({
    selector: 'app-header'
    , templateUrl: './header.component.html'
    , imports: [RouterLink, NgIf]
})

export class HeaderComponent implements OnInit
{
    isAuthenticated = false;
    private userSub = new Subscription;
    userName = '';
    userRole = '';
    uR = UserRole;

    constructor(private authService: AuthService)
    {

    }

    ngOnInit()
    {
        this.userSub = this.authService.user.subscribe(user =>
        {
            this.isAuthenticated = !!user;
            
            if(user != null)
                this.userName = user.name;
        });
    }

    onLogout()
    {
        this.authService.signOut();
    }

}