import { Lecture } from "./myCourses/lecture.model";

export class CourseReadModel
{
    constructor( 
        public refNumber : string
        , public title : String
        , public level : String
        , public category : String
        , public startDate: Date
        , public startTime: String
        , public days: string[]
        , public length: number
        , public duration : number
        , public status : string
        , public lectures : Array<Lecture>)
    {
        
    }
}