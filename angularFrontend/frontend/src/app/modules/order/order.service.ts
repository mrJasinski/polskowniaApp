import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";

@Injectable({providedIn: 'root'})
export class OrderService
{
    constructor(private http: HttpClient)
    {

    }

    getOrders()
    {
        return this.http.get(AppConstants.APP_URL + "/orders", {observe : 'response'} );
    }

    getAllOrders()
    {
        return this.http.get(AppConstants.APP_URL + "/allOrders", {observe : 'response'} );
    }
}