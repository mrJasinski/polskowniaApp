package polskowniaApp.shop;

import jakarta.persistence.*;
import polskowniaApp.shop.dto.ShopItemReadModel;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.LocalDateTime;

@Entity
@Table(name = "shop_items")
public class ShopItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refNumber;
    private String title;
    private double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int length;         //lessons amount
    private int duration;       //lesson duration (eg 45 min )
    @Enumerated(EnumType.STRING)
    private Level level;

    ShopItem()
    {
    }

    ShopItem(
            final String title
            , final double price
            , final String description
            , final Category category
            , final int length
            , final int duration
            , final Level level)
    {
        this.refNumber = generateRefNumber(category.getAcronym(), level.name());
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.length = length;
        this.duration = duration;
        this.level = level;
    }

    ShopItemReadModel toReadModel()
    {
        return new ShopItemReadModel(
                this.refNumber
                , this.title
                , this.price
                , this.description
                , this.category.getName()
                , this.length
                , this.duration
                , this.level.toString()
        );
    }

    String generateRefNumber(final String acronym , final String level)
    {
        var dt = LocalDateTime.now();
        var timestamp = String.format("%s%s%s%s%s%s%s", dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond(), dt.getNano());

        return acronym + "_" + level + "_" + timestamp;
    }
}
