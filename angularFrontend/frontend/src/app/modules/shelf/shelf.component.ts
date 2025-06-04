import { Component } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { ShelfService } from "./shelf.service";
import { FileWrapper } from "../fileManager/fileWrapper.model";
import { NgFor } from "@angular/common";

@Component
({
  selector: 'shelf',
  templateUrl: './shelf.component.html'
  , imports : [DashboardNavBarComponent, NgFor]
})

export class ShelfComponent 
{
    files : Array<FileWrapper>;

    constructor(private shelfService : ShelfService)
    {

    }

    ngOnInit()
    {
        this.shelfService.getMyShelf().subscribe( response => { this.files = <any>response.body});
    }

    onDownload(filename : string)
    {
        this.shelfService.downloadFile(filename)
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