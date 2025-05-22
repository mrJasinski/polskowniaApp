import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";
import { ShopItem } from "./shopItem.model";
import { ShopItemReadModel } from "./shopItemRead.model";
import { DiscountCode } from "./discountCode/discountCode.model";
import { NgForm } from "@angular/forms";

@Injectable({providedIn: 'root'})
export class ShopService
{
    shopItems = new Array<ShopItemReadModel>;
    itemRefNumber = '';

    constructor(private http: HttpClient)
    {

    }

    getShopItems()
    {
        return this.http.get(AppConstants.APP_URL + AppConstants.SHOP_URL, {observe : 'response'});
    }

    getShopItemByRefNumber(refNumber : String)
    {
        return this.http.get(AppConstants.APP_URL + AppConstants.SHOP_ITEM_URL + "/" + refNumber, { observe : 'response'});
    }

    getShopItemsCategories()
    {
        return this.http.get(AppConstants.APP_URL + '/getShopItemCategories', { observe : 'response' });
    }

    getDiscountCodes()
    {
        return this.http.get(AppConstants.APP_URL + '/getDiscountCodes', { observe : 'response' });
    }

    getDiscountValue(discountCode: string, cartSum: number) 
    {
        return this.http.post(AppConstants.APP_URL + '/getDiscount', 
            {
                discountCode : discountCode
                , cartSum : cartSum
            }, { observe : 'response' });
    }

    generateDiscountCode(code : DiscountCode)
    {
        return this.http.post<DiscountCode>(AppConstants.APP_URL + AppConstants.GENERATE_DISCOUNT_CODE_URL, 
            {
                code : code.code
                , value : code.value
                , type : code.type
                , startDate : code.startDate
                , endDate : code.endDate
            }, { observe : 'response' });
    }

    sendShopItemAddForm(item : ShopItem)
    {
        return this.http.post<ShopItem>(AppConstants.APP_URL + AppConstants.ADD_SHOP_ITEM_URL, 
            {
                title : item.title
                , price : item.price
                , description : item.description
                , category : item.category
                , length : item.length
                , duration : item.duration
                , level : item.level
            });
    }

    getItemRefNumber()
    {
        return this.itemRefNumber;
    }

    addItemToCart(item : ShopItemReadModel)
    {
        this.shopItems.push(item);
    }

    getItemsAddedToCart()
    {
        return this.shopItems;
    }
}