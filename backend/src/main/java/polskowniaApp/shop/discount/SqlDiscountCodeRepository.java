package polskowniaApp.shop.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
interface SqlDiscountCodeRepository extends DiscountCodeRepository, JpaRepository<DiscountCode, Integer>
{
    @Override
    @Query(value = "SELECT * FROM discount_codes WHERE discount_codes.code = :code", nativeQuery = true)
//    @Query(value = "SELECT * FROM discount_codes WHERE discount_codes.code = :code AND start_date < :date AND :date < end_date", nativeQuery = true)
    Optional<DiscountCode> findDiscountByNameAndDate(String code);
//    Optional<DiscountCode> findDiscountByNameAndDate(String code, LocalDate date);
}
