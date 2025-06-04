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
    downloadFile(filename : string)
    {
        return this.http.get(AppConstants.APP_URL + "/downloadFile/" + filename, 
            {
                observe : 'response'
                , responseType : 'blob'
            });
    }

    getMyShelf()
    {
        return this.http.get(AppConstants.APP_URL + "/myShelf", {observe : 'response'});
    }
}