package polskowniaApp.order;

import org.springframework.stereotype.Service;
import polskowniaApp.order.dto.OrderDTO;

@Service
class OrderService
{
    private final OrderRepository orderRepo;

    OrderService(OrderRepository orderRepo)
    {
        this.orderRepo = orderRepo;
    }

    Order createOrder(OrderDTO toSave)
    {
        return this.orderRepo.save(new Order
                (
//                TODO konstruktor
                ));
    }
}
