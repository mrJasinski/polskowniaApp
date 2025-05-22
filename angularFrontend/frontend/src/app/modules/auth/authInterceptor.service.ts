import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { exhaustMap, Observable, take } from "rxjs";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor
{
    constructor(private authService: AuthService)
    {

    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler) : Observable<HttpEvent<unknown>>
    {
        return this.authService.user
        .pipe(take(1), exhaustMap(user => 
            {
                if (!user)
                    return next.handle(request);

                const requestWithHeader = request.clone(
                    {
                        headers: request.headers.set('Authorization', `Bearer ${user.token}`),
                    }
                );

                return next.handle(requestWithHeader);
            }));

        
    }
}