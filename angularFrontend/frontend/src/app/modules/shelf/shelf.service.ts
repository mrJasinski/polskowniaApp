import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";

@Injectable({providedIn: 'root'})
export class ShelfService
{
    constructor(private http: HttpClient)
    {

    }

//just very test method
    downloadFile()
    {
        return this.http.get(AppConstants.APP_URL + "/downloadFile", 
            {
                observe : 'response'
                , responseType : 'blob'
            });
    }
}