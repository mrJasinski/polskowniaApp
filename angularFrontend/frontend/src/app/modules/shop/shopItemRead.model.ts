export class ShopItemReadModel
{
    constructor(
        public refNumber : string
        , public title : String
        , public price : number
        , public description : String
        , public category : String
        , public length : number
        , public duration : number
        , public level : String
        , public logo? : string
        )
    {
        
    }
}