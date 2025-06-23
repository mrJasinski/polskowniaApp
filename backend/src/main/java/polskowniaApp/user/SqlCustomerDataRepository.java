package polskowniaApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface SqlCustomerDataRepository extends CustomerDataRepository, JpaRepository<CustomerData, Integer>
{

    @Transactional
    @Modifying
    @Override
    @Query(value = "UPDATE customer_datas SET is_default = false WHERE user_id = :userId", nativeQuery = true)
    void setDefaultToFalseByUserId(int userId);

    @Override
    @Query(value = "SELECT * FROM customer_datas WHERE user_id = :userId AND is_default = true", nativeQuery = true)
    CustomerData findDefaultByUserId(int userId);
}
