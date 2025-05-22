package polskowniaApp.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import polskowniaApp.order.dto.OrderDTO;

@RestController
class OrderController
{
    private final OrderService orderService;

    public OrderController(final OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody OrderDTO toSave)
    {
        return ResponseEntity.ok(this.orderService.createOrder(toSave));
    }
}
