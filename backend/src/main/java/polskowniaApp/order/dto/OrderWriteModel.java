package polskowniaApp.order.dto;

import polskowniaApp.shop.dto.ShopItemReadModel;

import java.util.List;

public class OrderWriteModel
{
    private boolean isInvoice;
    private CustomerDataWriteModel customerData;
    private String paymentMethod;
    private String deliveryMethod;
    private List<String> shopItems;
    private String discountCode;
    private boolean isFourteenDays;
    private boolean isToCAccepted;
    private String comment;

    OrderWriteModel()
    {
    }

    OrderWriteModel(final boolean isInvoice, final CustomerDataWriteModel customerData, final String paymentMethod, final String deliveryMethod, final List<String> shopItems, final String discountCode, final boolean isFourteenDays, final boolean isToCAccepted, final String comment)
    {
        this.isInvoice = isInvoice;
        this.customerData = customerData;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.shopItems = shopItems;
        this.discountCode = discountCode;
        this.isFourteenDays = isFourteenDays;
        this.isToCAccepted = isToCAccepted;
        this.comment = comment;
    }

    public boolean isInvoice()
    {
        return this.isInvoice;
    }

    public CustomerDataWriteModel getCustomerData()
    {
        return this.customerData;
    }

    public String getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public String getDeliveryMethod()
    {
        return this.deliveryMethod;
    }

    public List<String> getShopItems()
    {
        return this.shopItems;
    }

    public String getDiscountCode()
    {
        return this.discountCode;
    }

    public boolean isFourteenDays()
    {
        return this.isFourteenDays;
    }

    public boolean isToCAccepted()
    {
        return this.isToCAccepted;
    }

    public String getComment()
    {
        return this.comment;
    }
}
