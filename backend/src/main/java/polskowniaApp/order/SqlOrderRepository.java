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
    void assignShopItemToOrder(int shopItemId, int orderId);

    @Override
    @Query(value = "SELECT id FROM orders WHERE user_id = :userId", nativeQuery = true)
    List<Integer> findOrderIdsByUserId(int userId);

    @Override
    @Query(value = "SELECT shop_item_id FROM ordered_items WHERE order_id IN :orderIds", nativeQuery = true)
    List<Integer> findShopItemsIdsByOrderIds(List<Integer> orderIds);

    @Override
    @Query(value = "SELECT shop_item_id FROM ordered_items WHERE order_id = :orderId", nativeQuery = true)
    List<Integer> findOrderedItemsIdsByOrderId(int orderId);
}
