import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";
import { CustomerData } from "./customerData.model.";

@Injectable({providedIn: 'root'})
export class UserService
{
    constructor(private http : HttpClient)
    {

    }

    saveCustomerData(data : CustomerData)
    {
        return this.http.post(AppConstants.APP_URL + "/sendCustomerData", data, {observe : 'response'});
    }

    getCustomerDataList()
    {
        return this.http.get(AppConstants.APP_URL + "/getCustomerDataList", { observe : 'response'});
    }

    getStudents()
    {
        return this.http.get(AppConstants.APP_URL + "/students", { observe : 'response'});
    }

    getDefaultCustomerData()
    {
        return this.http.get(AppConstants.APP_URL + "/getDefaultCustomerData", { observe : 'response'});
    }
}