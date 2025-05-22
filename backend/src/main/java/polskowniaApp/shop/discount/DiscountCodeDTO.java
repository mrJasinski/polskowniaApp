package polskowniaApp.shop.discount;

import java.time.LocalDate;

public class DiscountCodeDTO
{
    private String code;
    private int value;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    DiscountCodeDTO()
    {
    }

    DiscountCodeDTO(final String code, final int value, final String type, final LocalDate startDate, final LocalDate endDate)
    {
        this.code = code;
        this.value = value;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        setStatus();
    }

    public String getCode()
    {
        return this.code;
    }

    public int getValue()
    {
        return this.value;
    }

    public String getType()
    {
        return this.type;
    }

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public LocalDate getEndDate()
    {
        return this.endDate;
    }

    public String getStatus()
    {
        return this.status;
    }

    void setStatus()
    {
        var now = LocalDate.now();

        if (this.startDate.isAfter(now))
            this.status = "PLANOWANY";

        if (this.startDate.isBefore(now) && this.endDate.isAfter(now))
            this.status = "AKTYWNY";

        if (this.endDate.isBefore(now))
            this.status = "ARCHIWALNY";
    }
}
