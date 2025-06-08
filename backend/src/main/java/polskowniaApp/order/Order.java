package polskowniaApp.order;

import jakarta.persistence.*;
import polskowniaApp.order.dto.OrderReadModel;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.user.CustomerData;
import polskowniaApp.user.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refNumber;
    private LocalDate createdDate;
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
        this.createdDate = LocalDate.now();
        this.refNumber = generateNumber();
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
        return new OrderReadModel(
                this.refNumber
                , this.createdDate
                , this.status.name()
                , this.isInvoice
                , this.paymentMethod
                , this.deliveryMethod
                , this.comment
        );
    }

    private String generateNumber()
    {
//        two last digits of year
        var y = String.valueOf(this.createdDate.getYear()).substring(2, 4);
        var m = this.createdDate.getMonthValue();
        var d = this.createdDate.getDayOfMonth();

        var i = isInvoice ? "I" : "B";

        var u = 1000 + user.getId();

//        dodatkowo check w serwisie czy są inne zamówienia z tego dnia tego użytkownika i na końcu dodany numer kolejny? argument w konstruktorze

        return "O" + y + m + d + "_" + this.orderedItems.size() + "_" + i + u;
    }

    int getId()
    {
        return this.id;
    }


}
