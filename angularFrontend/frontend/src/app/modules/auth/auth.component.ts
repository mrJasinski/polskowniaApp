import { Component, OnInit } from "@angular/core";
import { AuthService } from "./auth.service";
import { Router } from "@angular/router";
import { FormControl, FormGroup, FormsModule, NgForm, NgModel } from "@angular/forms";
import { AppConstants } from "../../constans/app.constans";
import { NgIf } from "@angular/common";

@Component({
    selector: 'app-auth',
    templateUrl: './auth.component.html'
    , imports: [FormsModule, NgIf]
})
export class AuthComponent implements OnInit
{
    isLoginMode = true;

    authForm : FormGroup;

    constructor(private authService: AuthService, private router: Router)
    {

    }

    ngOnInit(): void 
    {
        this.authForm = new FormGroup(
            {
                'email' : new FormControl(null)
            });
    }

    onSwitchMode()
    {
        this.isLoginMode = !this.isLoginMode;
    }

    

    checkPasswords(password : string, confirmPassword : NgModel)
    {
        
    }

    onSubmit()
    {
    if (!this.authForm.valid)
    {
        return;
    }

    const email = this.authForm.value.email; 
    const password = this.authForm.value.password;

    if (this.isLoginMode)
    {
        this.authService.signIn(email, password) .subscribe(resData => 
        {
            this.router.navigate([AppConstants.DASHBOARD_URL]);
        }); 
    }

    if (!this.isLoginMode)
    {
        const name = this.authForm.value.name;


        this.authService.signUp(email, password, name).subscribe(resData => 
        {
            /* this.router.navigate(['/account']); */
            console.log(resData);
        });
    }

    this.authForm.reset();
    }

    onForgottenPassword()
    {
        // przypomnienie hasła
        // przejście do formularza który pyta o email
        // zapytanie do bazy czy emial jest poprawny i znany
        // jeśli tak to wysłanie na email tymczasowych danych logowania generowanych przez system
        // po zalogowaniu wymagana zmiana hasła
    }

}