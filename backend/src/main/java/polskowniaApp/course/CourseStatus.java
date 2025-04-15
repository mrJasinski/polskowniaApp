package polskowniaApp.course;

public enum CourseStatus
{
    CREATED     // newly created course before first lecture
    , ONGOING   // status automatically set by system after first lesson -> course started
    , ENDED     // set by lecturer after last lecture
}
