package polskowniaApp.order;

import java.util.List;

interface OrderRepository
{
    Order save(Order toSave);

    void assignShopItemsToOrder(int shopItemId, int orderId);

    List<Order> findByUserId(int userId);

    List<Integer> findOrderIdsByUserId(int userId);

    List<Integer> findShopItemsIdsByOrderIds(List<Integer> orderIds);

    List<Order> findAll();
}
