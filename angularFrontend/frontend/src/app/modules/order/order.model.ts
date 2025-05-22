import { CustomerData } from "./customerData.model";

export class Order
{
    constructor
    (
        public isInvoice : boolean
        , public customerData : CustomerData
        , public paymentMethod : string     // object I guess
        , public deliveryMetod : string     // object I guess
        , public comment : string           // uwagi do zamówienia
        , public cart : string              // list<shopItem>?
        , public discountCode : string
        , public isFourteenDays : boolean   // klauzula o 14 dnaich dla materiałów elektronicznych
        , public isToCAccepted : boolean    // akceptacja regulaminu strony/sklepu    
        , public invoiceData? : CustomerData     // jeśli są inne dane do faktury
        , public addressData? : CustomerData // jeśli inny adres dostawy    
    )
    {

    }
}