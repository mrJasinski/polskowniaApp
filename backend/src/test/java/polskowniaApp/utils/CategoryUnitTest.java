package polskowniaApp.utils;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;

class CategoryUnitTest
{
//Category getByName(String name)
//    {
//        return Arrays.stream(Category.values()).filter(c -> c.getName().equals(name)).toList().get(0);
//    }

    @Test
    void getByName_shouldReturnEnumWhenGivenNameFound()
    {
//        given
        var name = "Kurs indywidualny";

//        when
        var result = Category.getByName(name);

//        then
        assertEquals(Category.COURSE_INDIVIDUAL, result);
    }

    @Test
    void getByName_shouldThrowExceptionWhenGivenNameNotFound()
    {
//        given
        var name = "Nazwa kategorii";

//        when
        var result = catchThrowable(() -> Category.getByName(name));

//        then
        assertThat(result)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Category with given name not found!");
    }
}