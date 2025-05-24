package polskowniaApp.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
interface SqlOrderRepository extends OrderRepository, JpaRepository<Order, Integer>
{
    @Override
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ordered_items (order_id, shop_item_id) VALUES (:orderId, :shopItemId)", nativeQuery = true)
    void assignShopItemsToOrder(int shopItemId, int orderId);
}
