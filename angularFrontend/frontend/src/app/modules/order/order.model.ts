import { ShopItem } from "../shop/shopItem.model";
import { CustomerData } from "./customerData.model";

export class Order
{
    constructor
    (
        public isInvoice : boolean
        , public customerData : CustomerData
        , public paymentMethod : string     // object I guess
        , public deliveryMethod : string     // object I guess
        , public cart : Array<String>              // list<shopItem>?
        , public discountCode : string
        , public isFourteenDays : boolean   // klauzula o 14 dnaich dla materiałów elektronicznych
        , public isToCAccepted : boolean    // akceptacja regulaminu strony/sklepu
        , public comment? : string           // uwagi do zamówienia    
        , public invoiceData? : CustomerData     // jeśli są inne dane do faktury
        , public addressData? : CustomerData // jeśli inny adres dostawy  
        , public refNumber? : string
        , public status? : string  
    )
    {

    }
}