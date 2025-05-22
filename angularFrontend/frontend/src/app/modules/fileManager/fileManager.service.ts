import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";

@Injectable({providedIn: 'root'})
export class FileManagerService
{
    constructor(private http: HttpClient)
    {

    }

    uploadFile(formData : FormData)
    {
        return this.http.post(AppConstants.APP_URL + "/uploadFile", formData).subscribe(response => {});
    }

    getFiles()
    {
        return this.http.get(AppConstants.APP_URL + "/getFiles", {observe : 'response'});
    }
}