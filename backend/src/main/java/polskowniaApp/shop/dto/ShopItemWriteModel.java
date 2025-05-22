package polskowniaApp.shop.dto;

public class ShopItemWriteModel
{
    private String title;
    private double price;
    private String description;
    private String category;
    private int length;
    private int duration;
    private String level;

    ShopItemWriteModel()
    {
    }

    ShopItemWriteModel(final String title, final double price, final String description, final String category, final int length, final int duration, final String level)
    {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.length = length;
        this.duration = duration;
        this.level = level;
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
