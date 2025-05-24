import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";
import { BehaviorSubject, tap } from "rxjs";
import { User } from "./user.model";
import { Token } from "./token.model";
import { Router } from "@angular/router";

@Injectable({ providedIn: 'root' })
export class AuthService
{
    user = new BehaviorSubject<User | null>(null);
    token = new BehaviorSubject<Token | null>(null);
    private tokenTimer : any;
    userRole = '';
 
    constructor(private http: HttpClient, private router: Router)
    {

    }

    signUp(email: string, password: string, name : string)
    {
        return this.http.post(AppConstants.APP_URL + AppConstants.REGISTER_API_URL
            , { 
                email : email
                , password : password
                , name : name
            });
    }

    getUserRole()
    {            
        return this.userRole;
    }

    signIn(email: string, password: string)
    {
        return this.http.post<User>(
            AppConstants.APP_URL + AppConstants.AUTH_API_URL, 
            { 
                email : email
                , password : password
            })
             .pipe(tap(resData => 
                {
                    const user = new User(
                                        resData.name
                                        , resData.email
                                        , resData.token
                                        , resData.role);
                     
                    this.user.next(user);

                    this.userRole = user.role;

                    //tymczasowo wykomentowane aby móc pracować - poprawić w wolnej chwili
                    //this.autoSignOut(this.tokenExpiration(resData.token) * 1000);

                    localStorage.setItem('userData', JSON.stringify(user));
                 })); 
    }

    autoLogin()
    {
        
       const userData:
       {
        name: string
        , email: string
        , token: string
        , role: string
       }= JSON.parse(localStorage.getItem('userData')!);

       if (!userData)
        return;

        const loadedUser = new User(
            userData.name
            , userData.email
            ,userData.token
            , userData.role
        );

        if (loadedUser.token)
        {
            this.user.next(loadedUser);
            const expiry = new Date(this.tokenExpiration(loadedUser.token)).getTime() - new Date().getTime();
            this.autoSignOut(expiry);
        }
    }

    signOut()
    {        
        this.user.next(null);
        localStorage.removeItem('userData');
        this.router.navigate([AppConstants.HOME_URL]);

        if(this.tokenTimer)
            clearTimeout(this.tokenTimer);

        this.tokenTimer = null;
    }

    autoSignOut(tokenExpiration : number)
    {
        //toeknExpiration is in miliseconds
        this.tokenTimer = setTimeout(() => {this.signOut()}, tokenExpiration);
    }

    private tokenExpiration(token: string) {
        return (JSON.parse(atob(token.split('.')[1]))).exp;
      }
}