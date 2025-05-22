import { Component } from "@angular/core";
import { DashboardNavBarComponent } from "../dashboard/dashboardNavBar.component";
import { FileManagerService } from "./fileManager.service";
import { NgFor } from "@angular/common";
import { FileWrapper } from "./fileWrapper.model";

@Component({
    selector: 'file-manager',
    templateUrl: './fileManager.component.html',
    imports: [DashboardNavBarComponent, NgFor]
    
})
export class FileManagerComponent
{
    uploadedFile : File;

    fileList = new Array<FileWrapper>();

    constructor(private fileService : FileManagerService)
    {

    }

    ngOnInit()
    {
        //very test method
        this.fileService.getFiles().subscribe(response => {this.fileList = <any> response.body});
    }

    onFileSelected($event) 
    {
        this.uploadedFile = $event.target.files[0];
    }

    onUpload()
    {
        const formData = new FormData();
        formData.append("uploadedFile", this.uploadedFile);

        this.fileService.uploadFile(formData);
    }
}