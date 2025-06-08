export class ShopItemReadModel
{
    constructor(
        public refNumber : string
        , public title : string
        , public price : number
        , public description : string
        , public category : string
        , public length : number
        , public duration : number
        , public level : string
        , public logo? : string
        )
    {
        
    }
}