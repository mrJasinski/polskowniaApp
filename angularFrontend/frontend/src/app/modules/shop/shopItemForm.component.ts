import { Component, ViewChild } from "@angular/core";
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ShopService } from "./shop.service";
import { NgFor, NgIf } from "@angular/common";
import { ShopItem } from "./shopItem.model";
import { levels } from "../../constans/levels.constant";
import { FileManagerService } from "../fileManager/fileManager.service";


@Component
({
  selector: 'shop-item-form',
  templateUrl: './shopItemForm.component.html'
  , imports : [ReactiveFormsModule, NgFor, NgIf]
})

export class ShopItemFormComponent 
{
    categories = new Array<String>;
    levels = levels;
    selectedLevels = new Array<String>;
    
    item : ShopItem;
    shopItemForm : FormGroup;
    isUpdate = false;
    resultText : string;
    uploadedFile : File;
    uploadedLogo : File;

    constructor(private shopService : ShopService, private fileService : FileManagerService)
    {

    }

    ngOnInit()
    {
        this.shopItemForm = new FormGroup(
            {
                'refNumber' : new FormControl(null)
                , 'title' : new FormControl(null)
                , 'price' : new FormControl(null)
                , 'description' : new FormControl(null) 
                , 'category' : new FormControl(null) 
                , 'length' : new FormControl(null) 
                , 'duration' : new FormControl(null) 
                , 'level' : new FormControl(null)  
               // , 'levels' : new FormControl(null)  
                , 'file' : new FormControl(null)
                , 'logo' : new FormControl(null)  
            });

            
        this.shopService.getShopItemsCategories().subscribe(response => 
        {
          this.categories = <any>response.body;
        });

        const refNumber = this.shopService.getItemRefNumber();

        if ( refNumber != '')
            {
                this.isUpdate = true;

                this.shopService.getShopItemByRefNumber(refNumber).subscribe((response) => { this.item = <any>response.body 

                    this.shopItemForm.patchValue
                    ({
                        'refNumber' : this.item.refNumber
                        ,'title' : this.item.title
                        , 'price' : this.item.price
                        , 'description' : this.item.description
                        , 'category' : this.item.category
                        , 'length' : this.item.length
                        , 'duration' : this.item.duration
                        , 'level' : this.item.level
                    });
                }); 
            }        
    }

    onSubmit()
    {
        const item = new ShopItem(
            this.shopItemForm.value.title
            , this.shopItemForm.value.price
            , this.shopItemForm.value.description
            , this.shopItemForm.value.category
            , this.shopItemForm.value.length
            , this.shopItemForm.value.duration
            , this.shopItemForm.value.level
        );

        //przesłanie pliku
        const formData = new FormData();
        formData.append('uploadedFile', this.uploadedFile);
        formData.append('uploadedLogo', this.uploadedLogo);
        formData.append('item', JSON.stringify(item));

        this.shopService.sendShopItemAddForm(formData).subscribe(response => 
        //this.shopService.sendShopItemAddForm(item).subscribe(response => 
            {
                this.shopItemForm.reset();

                this.resultText = 'Pomyślnie dodano/zaktualizowno!';
            });
    }

    onLevelsSelection($event)
    {
        const level = $event.target.value;
        const isChecked = $event.target.checked;

       // const index = this.levels.indexOf(level);

        // zaznaczenie checkboxa dodaje jego index z tablicy weekdays który potem może być przetłumaczony w backenddzie na DayOfWeek

         if (isChecked && !this.selectedLevels.includes(level))
             this.selectedLevels.push(level);

         if(!isChecked && this.selectedLevels.includes(level))
         {
             const i = this.selectedLevels.indexOf(level);
             this.selectedLevels.splice(i, 1);
         }

         console.log("l " + this.selectedLevels);
    }

    onFileSelected($event) 
    {
        this.uploadedFile = $event.target.files[0];
    }

    onLogoSelected($event) 
    {
        this.uploadedLogo = $event.target.files[0];
    }

    onUpload()
    {
        const formData = new FormData();
        formData.append("uploadedFile", this.uploadedFile);

        this.fileService.uploadFile(formData);
    }
}