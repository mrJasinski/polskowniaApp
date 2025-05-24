package polskowniaApp.order;

import jakarta.persistence.*;
import polskowniaApp.shop.ShopItem;

@Entity
@Table(name = "ordered_items")
public class OrderedItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "shop_item_id")
    private ShopItem shopItem;
}
