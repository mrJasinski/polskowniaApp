package polskowniaApp.shop;

import org.junit.jupiter.api.Test;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.shop.discount.DiscountCodeRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ShopServiceUnitTest
{
//double getDiscountValue(final String code, final double cartSum)
//    {
//        var discountCode = this.discountCodeRepo.getDiscountByName(code)
//                .orElseThrow(() -> new NoSuchElementException("Discount code not found!"));
//
//        var discountValue = 0d;
//        var codeValue = discountCode.getValue();
//
//        switch (discountCode.getType())
//        {
//            case AMOUNT -> discountValue = cartSum - codeValue;
//            case PERCENT -> discountValue = cartSum - ((codeValue * cartSum) / 100);
//        }
//
//        return discountValue;
//    }

    @Test
    void getDiscountValue_shouldReturnLoweredCartSumWithDiscountTypeAmount()
    {
//        given
        var cartSum = 100;
        var code = "FIOLET10";
        var discountCode = new DiscountCode(code, 10, "PLN", LocalDate.now(), LocalDate.now().plusWeeks(1));

        var mockDiscountCodeRepo = mock(DiscountCodeRepository.class);
        given(mockDiscountCodeRepo.findDiscountByNameAndDate(anyString())).willReturn(Optional.of(discountCode));

//        system under test
        var toTest = new ShopService(null, mockDiscountCodeRepo);

//        when
        var result = toTest.getDiscountValue(code, cartSum);

//        then
        assertEquals(10, result);
    }

    @Test
    void getDiscountValue_shouldReturnLoweredCartSumWithDiscountTypePercent()
    {
//        given
        var cartSum = 100;
        var code = "FIOLET10";
        var discountCode = new DiscountCode(code, 10, "%", LocalDate.now(), LocalDate.now().plusWeeks(1));

        var mockDiscountCodeRepo = mock(DiscountCodeRepository.class);
        given(mockDiscountCodeRepo.findDiscountByNameAndDate(anyString())).willReturn(Optional.of(discountCode));

//        system under test
        var toTest = new ShopService(null, mockDiscountCodeRepo);

//        when
        var result = toTest.getDiscountValue(code, cartSum);

//        then
        assertEquals(10, result);
    }

}