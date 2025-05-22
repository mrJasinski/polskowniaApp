package polskowniaApp.shop.discount;

public class CodeDTO
{
//    TODO lepsza nazwa - wrapper dla żądania "/getDiscount" zawierającego nazwę kodu i wartość koszyka

    private String discountCode;
    private double cartSum;

    CodeDTO()
    {
    }

    CodeDTO(final String discountCode, final double cartSum)
    {
        this.discountCode = discountCode;
        this.cartSum = cartSum;
    }

    public String getDiscountCode()
    {
        return this.discountCode;
    }

    public double getCartSum()
    {
        return this.cartSum;
    }
}
