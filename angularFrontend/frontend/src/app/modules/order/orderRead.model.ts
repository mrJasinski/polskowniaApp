import { ShopItemReadModel } from "../shop/shopItemRead.model";

export class OrderReadModel
{
    constructor
    (
        public refNumber : string
        , public createdDate : Date
        , public status : string
        , public invoice : boolean
        , public paymentMethod : string
        , public deliveryMethod : string
        , public fourteenDays : boolean
        , public tocAccepted : boolean
        , public comment : string
        , public items : Array<ShopItemReadModel>
    )
    {

    }
}