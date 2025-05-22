export class Course
{
    constructor( 
        public startDate: Date
        , public startTime: String
        , public days: number[]
        , public length: number
        , public duration : number
        , public participants : Array<String>)
    {
        
    }
}