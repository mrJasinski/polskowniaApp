package polskowniaApp.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCustomerDataRepository extends CustomerDataRepository, JpaRepository<CustomerData, Integer>
{
}
