package polskowniaApp.shop.discount;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository
{
    List<DiscountCode> findAll();

    DiscountCode save(DiscountCode toSave);

    Optional<DiscountCode> findDiscountByNameAndDate(String code);
//    Optional<DiscountCode> findDiscountByNameAndDate(String code, LocalDate date);
}
