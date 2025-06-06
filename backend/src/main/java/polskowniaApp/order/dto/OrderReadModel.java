package polskowniaApp.order.dto;

import polskowniaApp.shop.dto.ShopItemReadModel;

import java.util.List;

public class OrderReadModel
{
    private String refNumber;
    private String status;
    private boolean isInvoice;
    private String paymentMethod;
    private String deliveryMethod;
    private boolean isFourteenDays;
    private boolean isTocAccepted;
    private String comment;
    private List<ShopItemReadModel> items;


    public OrderReadModel()
    {
    }

    public OrderReadModel(final String refNumber, final String status, final boolean isInvoice, final String paymentMethod, final String deliveryMethod, final String comment)
    {
        this.refNumber = refNumber;
        this.status = status;
        this.isInvoice = isInvoice;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.comment = comment;
    }

    public String getRefNumber()
    {
        return this.refNumber;
    }

    public String getStatus()
    {
        return this.status;
    }

    public boolean isInvoice()
    {
        return this.isInvoice;
    }

    public String getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public String getDeliveryMethod()
    {
        return this.deliveryMethod;
    }

    public boolean isFourteenDays()
    {
        return this.isFourteenDays;
    }

    public boolean isTocAccepted()
    {
        return this.isTocAccepted;
    }

    public String getComment()
    {
        return this.comment;
    }

    public List<ShopItemReadModel> getItems()
    {
        return this.items;
    }

    public void setItems(final List<ShopItemReadModel> items)
    {
        this.items = items;
    }
}
