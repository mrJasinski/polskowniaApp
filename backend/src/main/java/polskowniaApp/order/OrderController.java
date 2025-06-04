package polskowniaApp.order;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import polskowniaApp.order.dto.OrderWriteModel;
import polskowniaApp.security.JwtService;

@RestController
class OrderController
{
    private final OrderService orderService;
    private final JwtService jwtService;

    public OrderController(final OrderService orderService, final JwtService jwtService)
    {
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    @PostMapping("/createOrder")
    ResponseEntity<?> createOrder(@RequestBody OrderWriteModel toSave, HttpServletRequest request)
    {
        return ResponseEntity.ok(this.orderService.createOrder(toSave, this.jwtService.getUserEmail(request)));
    }

    @GetMapping("/orders")
    ResponseEntity<?> getOrders(HttpServletRequest request)
    {
        return ResponseEntity.ok(this.orderService.getOrdersByUserIdAsReadModel(this.jwtService.getUserIdFromToken(request)));
    }

    @GetMapping("/allOrders")
    ResponseEntity<?> getAllOrders()
    {
        return ResponseEntity.ok(this.orderService.getAllOrdersAsReadModel());
    }

    @GetMapping("/myShelf")
    ResponseEntity<?> getMyShelf(HttpServletRequest request)
    {
        return ResponseEntity.ok(this.orderService.getMyShelfByUserId(this.jwtService.getUserIdFromToken(request)));
    }
}
