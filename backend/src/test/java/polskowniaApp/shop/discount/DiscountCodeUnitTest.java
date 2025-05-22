package polskowniaApp.shop.discount;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCodeUnitTest
{
//    private Type convertTypeToEnum(final String type)
//    {
//        for (Type t : Type.values())
//            if (type.matches(t.getName()))
//                return t;
//
//        throw new IllegalArgumentException("No enum of given type found!");
//    }

    @Test
    void convertTypeToEnum_shouldReturnAmountEnumWhenTypePLNIsProvided()
    {
//        given
        var type = "PLN";

//        system under test
        var toTest = new DiscountCode();

//        when
        var result = toTest.convertTypeToEnum(type);

//        then
        assertEquals(DiscountType.AMOUNT, result);
    }

    @Test
    void convertTypeToEnum_shouldReturnPercentEnumWhenTypePercentIsProvided()
    {
//        given
        var type = "%";

//        system under test
        var toTest = new DiscountCode();

//        when
        var result = toTest.convertTypeToEnum(type);

//        then
        assertEquals(DiscountType.PERCENT, result);
    }

    @Test
    void convertTypeToEnum_shouldThrowExceptionWhenWrongTypeStringIsIsProvided()
    {
//        given
        var type = "zły";

//        system under test
        var toTest = new DiscountCode();

//        when
        var result = catchThrowable(() -> toTest.convertTypeToEnum(type));

//        then
        assertThat(result)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("No enum of given type found!");
    }

}