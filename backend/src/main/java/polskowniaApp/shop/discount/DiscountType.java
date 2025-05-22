package polskowniaApp.shop.discount;

public enum DiscountType
{
    PERCENT("%")
    , AMOUNT("PLN");

    private String name;

    DiscountType(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
