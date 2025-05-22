package polskowniaApp.shop.dto;

import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

public class ShopItemReadModel
{
    private String refNumber;
    private String title;
    private double price;
    private String description;
    private String category;
    private int length;
    private int duration;
    private String level;

    ShopItemReadModel()
    {
    }

    public ShopItemReadModel(
            final String refNumber
            ,final String title
            , final double price
            , final String description
            , final String category
            , final int length
            , final int duration
            , final String level)
    {
        this.refNumber = refNumber;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.length = length;
        this.duration = duration;
        this.level = level;
    }

    public String getRefNumber()
    {
        return this.refNumber;
    }

    public String getTitle()
    {
        return this.title;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getCategory()
    {
        return this.category;
    }

    public int getLength()
    {
        return this.length;
    }

    public int getDuration()
    {
        return this.duration;
    }

    public String getLevel()
    {
        return this.level;
    }
}
