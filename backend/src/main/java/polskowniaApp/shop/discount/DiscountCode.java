package polskowniaApp.shop.discount;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Entity
@Table(name = "discount_codes")
public class DiscountCode
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private int value;
    @Enumerated(EnumType.STRING)
    private DiscountType type;
    private LocalDate startDate;
    private LocalDate endDate;

//    TODO
//    minimalna wartość zamówienia jako opcja
//    dodatkowe kody tylko dla nowych itd

    DiscountCode()
    {
    }

    public DiscountCode(final String code, final int value, final String type, final LocalDate startDate, final LocalDate endDate)
    {
        this.code = code;
        this.value = value;
        this.type = convertTypeToEnum(type);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    DiscountType convertTypeToEnum(final String type)
    {
        for (DiscountType t : DiscountType.values())
            if (type.matches(t.getName()))
                return t;

        throw new NoSuchElementException("No enum of given type found!");
    }

    public DiscountCodeDTO toDto()
    {
        return new DiscountCodeDTO(
                this.code
                , this.value
                , this.type.getName()
                , this.startDate
                , this.endDate
        );
    }

    int getId()
    {
        return id;
    }

    public String getCode()
    {
        return this.code;
    }

    public int getValue()
    {
        return this.value;
    }

    public DiscountType getType()
    {
        return this.type;
    }

    LocalDate getStartDate()
    {
        return this.startDate;
    }

    LocalDate getEndDate()
    {
        return this.endDate;
    }
}
