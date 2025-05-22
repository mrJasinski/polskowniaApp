export class DiscountCode
{
    constructor(
        public code : string
        , public value : number
        , public type : string
        , public startDate : Date
        , public endDate : Date
        , public status? : string
    )
    {
        
    }
}