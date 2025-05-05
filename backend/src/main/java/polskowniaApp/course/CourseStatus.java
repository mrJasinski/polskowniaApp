package polskowniaApp.course;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public enum CourseStatus
{
    CREATED("Utworzony")     // newly created course before first lecture
    , ONGOING("W trakcie")   // status automatically set by system after first lesson -> course started
    , CANCELLED("Anulowany")
    , ENDED("Ukończony");     // set by lecturer after last lecture ; ;

    private String name;

    CourseStatus(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
