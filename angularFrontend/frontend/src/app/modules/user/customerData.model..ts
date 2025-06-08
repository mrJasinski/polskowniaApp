export class CustomerData
{
    constructor
    (
        public isDefault : boolean
        , public firstName : string
        , public lastName : string
        , public streetName : string
        , public streetNumber : string
        , public zipCode : string
        , public town : string
        , public phone : string
        , public email : string
        , public localNumber? : string
        , public company? : string
        , public taxNumber? : string
    )
    {
    }
}