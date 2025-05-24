package polskowniaApp.order.dto;

import polskowniaApp.order.OrderedItem;

import java.util.List;

public class OrderReadModel
{
    private String number;
    private String status;
    private boolean isInvoice;
    private String paymentMethod;
    private String deliveryMethod;
    private boolean isFourteenDays;
    private boolean isTocAccepted;
    private String comment;
    //private List<OrderedItem> orderedItems;


    OrderReadModel()
    {
    }

    OrderReadModel(final String number, final String status, final boolean isInvoice, final String paymentMethod, final String deliveryMethod, final String comment)
    {
        this.number = number;
        this.status = status;
        this.isInvoice = isInvoice;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.comment = comment;
    }

    public String getNumber()
    {
        return this.number;
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

    public String getComment()
    {
        return this.comment;
    }
}
