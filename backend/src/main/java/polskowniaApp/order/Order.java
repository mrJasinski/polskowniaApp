package polskowniaApp.order;

import jakarta.persistence.*;
import polskowniaApp.order.dto.OrderReadModel;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.user.User;

import java.util.List;

@Entity
@Table(name = "orders")
class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private boolean isInvoice;
    private String paymentMethod;
    private String deliveryMethod;
    private boolean isFourteenDays;
    private boolean isTocAccepted;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;
    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;
    @ManyToOne
    @JoinColumn(name = "customer_data_id")
    private CustomerData customerData;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    Order()
    {
    }

    Order(final boolean isInvoice, final String paymentMethod, final String deliveryMethod, final boolean isFourteenDays, final boolean isTocAccepted, final String comment, final DiscountCode discountCode, final CustomerData customerData, final User user)
    {
        this.number = generateNumber();
        this.status = OrderStatus.CREATED;
        this.isInvoice = isInvoice;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.isFourteenDays = isFourteenDays;
        this.isTocAccepted = isTocAccepted;
        this.comment = comment;
        this.discountCode = discountCode;
        this.customerData = customerData;
        this.user = user;
    }

    OrderReadModel toReadModel()
    {
//        TODO
        return null;
    }

    private String generateNumber()
    {
//        TODO
        return "refNumber";
    }

    int getId()
    {
        return this.id;
    }


}
