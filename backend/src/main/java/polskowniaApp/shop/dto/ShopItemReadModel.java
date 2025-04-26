package polskowniaApp.shop.dto;

import polskowniaApp.shop.ShopItem;

public class ShopItemReadModel
{
    private String title;
    private double price;
    private String description;
    private String category;
    private int length;
    private int duration;
    private String level;

    public ShopItemReadModel(
            final String title
            , final double price
            , final String description
            , final ShopItem.Category category
            , final int length
            , final int duration
            , final ShopItem.Level level)
    {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category.name();
        this.length = length;
        this.duration = duration;
        this.level = level.toString();
    }
}
