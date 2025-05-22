import { Component } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { ShelfService } from "./shelf.service";

@Component
({
  selector: 'shelf',
  templateUrl: './shelf.component.html'
  , imports : [DashboardNavBarComponent]
})

export class ShelfComponent 
{
    constructor(private shelfService : ShelfService)
    {

    }

    onDownload()
    {
        this.shelfService.downloadFile()
        .subscribe(response =>
        {
            let fileName = response.headers.get('content-disposition')!
            .split(';')[1].split('=')[1];

            let blob : Blob = response.body as Blob;

            let a = document.createElement('a');
            a.download = fileName;
            a.href = window.URL.createObjectURL(blob);
            a.click();
        });
    }
}