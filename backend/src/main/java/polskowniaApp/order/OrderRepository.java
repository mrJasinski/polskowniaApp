package polskowniaApp.order;

import java.util.List;
import java.util.Optional;

interface OrderRepository
{
    Order save(Order toSave);

    void assignShopItemToOrder(int shopItemId, int orderId);

    List<Order> findByUserId(int userId);

    List<Integer> findOrderIdsByUserId(int userId);

    List<Integer> findShopItemsIdsByOrderIds(List<Integer> orderIds);

    List<Order> findAll();

    Optional<Order> findByRefNumber(String refNumber);

    List<Integer> findOrderedItemsIdsByOrderId(int orderId);
}
