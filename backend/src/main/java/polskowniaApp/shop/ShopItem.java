package polskowniaApp.shop;

import jakarta.persistence.*;
import polskowniaApp.shop.dto.ShopItemReadModel;

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
        this.refNumber = generateRefNumber();
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
                , this.category
                , this.length
                , this.duration
                , this.level
        );
    }

    String generateRefNumber()
    {
        //TODO how to generate?
        //skrótowiec kategorii + numer kolejny?
        return "refNumber";
    }

    public enum Category
    {
        COURSE_INDIVIDUAL("Kurs indywidualny")
        , COURSE_GROUP("Kurs grupowy")
        , EXAM_SIMULATION("Symulacja egzaminu")
        , BOOK("Książka")
        , EBOOK("Ebook")
        , EXERCISE_SHEET("Arkusz ćwiczeń");

        private final String name;

        Category(final String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public enum Level
    {
        A0
        , A1
        , A2
        , B1
        , B2
        , C1
        , C2
    }

}
