import { Component } from "@angular/core";
import { ShopService } from "./shop.service";
import { NgFor, NgIf } from "@angular/common";
import { AuthService } from "../auth/auth.service";
import { UserRole } from "../../constans/role.constans";
import { Router, RouterLink } from "@angular/router";
import { AppConstants } from "../../constans/app.constans";
import { ShopItemReadModel } from "./shopItemRead.model";
import { levels } from "../../constans/levels.constant";

@Component
({
  selector: 'shop',
  templateUrl: './shop.component.html'
  , imports: [NgFor, NgIf, RouterLink]
})

export class ShopComponent 
{
    shopItems = new Array<ShopItemReadModel>;
    filteredShopItems = new Array<ShopItemReadModel>;
    categories = new Array<String>;
    selectedCategories = new Array<String>;
    levels = levels;
    selectedLevels = new Array<String>;

    userRole = '';
    uR = UserRole;
        
    constructor(private shopService: ShopService, private authService : AuthService, private router: Router)
    {
    }

    ngOnInit()
    {
      this.userRole = this.authService.getUserRole();

      this.shopService.getShopItemsCategories().subscribe(response => 
        {
          this.categories = <any>response.body;
        });

      this.shopService.getShopItems().subscribe(response => 
        {
          this.shopItems = <any>response.body;
          this.filteredShopItems = this.shopItems;
        });
    }

    onManageDiscountCodes()
    {
      this.router.navigate([AppConstants.DISCOUNT_CODES_URL]);
    }

    onAddToCart(item : ShopItemReadModel)
    {
      // dodawanie kursów do koszyka - jak ustalać terminarz?
      // kupujący podaje swoją dostępność? i następnie ręczna weryfikacja przez nauczyciela?
      // kalendarz typu booksy z wolnymi slotami lektora?

        this.shopService.addItemToCart(item);
    }

    onSelectionCategory($event)
    {
      //założenia
      // zaznaczenie 'wszystko' odznacza inne wybrane opcje i wyświetla wszystkie pozycje na sklepie
      // zaznaczenie jednej lub więcej kategorii filtruje pozycje dostępne na sklepie wg wybranych kategorii

      const selected = $event.target.value;
      const isChecked = $event.target.checked;

      // jak obsłużyć zmianę zaznaczenia?
      // 'wszystkie' odznacza pozostałe checkboxy
      // dowolny inny odznacza 'wszystkie'

     // if (selected != this.categories.at(0))
     // {
        if (isChecked)
          this.selectedCategories.push(selected);
  
        if(!isChecked)
        {
            const i = this.selectedCategories.indexOf(selected);
            this.selectedCategories.splice(i, 1);
        }

        if (this.selectedCategories.length == 0)
          this.filteredShopItems = this.shopItems;
  
        if (this.selectedCategories.length > 0)
          this.filteredShopItems = this.shopItems.filter(item => this.selectedCategories.includes(item.category));
     // }

      //if (selected == this.categories.at(0))
      //  this.filteredShopItems = this.shopItems;
    }

    onSelectionLevel($event)
    {
      const selected = $event.target.value;
      const isChecked = $event.target.checked;

      if (isChecked)
          this.selectedLevels.push(selected);
  
        if(!isChecked)
        {
            const i = this.selectedLevels.indexOf(selected);
            this.selectedLevels.splice(i, 1);
        }

        if (this.selectedLevels.length == 0)
          this.filteredShopItems = this.shopItems;
  
        if (this.selectedLevels.length > 0)
          this.filteredShopItems = this.shopItems.filter(item => this.selectedLevels.includes(item.level));
    }

    onAddShopItem()
    {
      this.router.navigate([AppConstants.ADD_SHOP_ITEM_URL]);
    }

    onDeleteShopItem()
    {
      //usunięcie pozycji z listy - tylko dla nauczyciela/admina
    }

    onEditShopItem(refNumber : string)
    {
      this.shopService.itemRefNumber = refNumber;
      this.router.navigate([AppConstants.ADD_SHOP_ITEM_URL + "/" + refNumber]);
    }

    onShowShopItem(refNumber : string)
    {
      console.log('xxx ' + refNumber);

      this.shopService.itemRefNumber = refNumber;

      this.router.navigate([AppConstants.SHOP_ITEM_URL + "/" + refNumber])
    }
}