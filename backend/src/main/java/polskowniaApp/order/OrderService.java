package polskowniaApp.order;

import org.springframework.stereotype.Service;
import polskowniaApp.order.dto.CustomerDataWriteModel;
import polskowniaApp.order.dto.OrderReadModel;
import polskowniaApp.order.dto.OrderWriteModel;
import polskowniaApp.shop.ShopService;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.user.UserService;

import java.util.List;

@Service
class OrderService
{
    private final OrderRepository orderRepo;
    private final ShopService shopService;
    private final CustomerDataRepository customerDataRepo;
    private final UserService userService;

    OrderService(OrderRepository orderRepo, final ShopService shopService, final CustomerDataRepository customerDataRepo, final UserService userService)
    {
        this.orderRepo = orderRepo;
        this.shopService = shopService;
        this.customerDataRepo = customerDataRepo;
        this.userService = userService;
    }

    CustomerData createCustomerData(final CustomerDataWriteModel toSave)
    {
        return this.customerDataRepo.save(new CustomerData(
                        toSave.getFirstName()
                        , toSave.getLastName()
                        , toSave.getStreetName()
                        , toSave.getStreetNumber()
                        , toSave.getLocalNumber()
                        , toSave.getZipCode()
                        , toSave.getTown()
                        , toSave.getPhone()
                        , toSave.getEmail()
                ));
    }

    Order createOrder(final OrderWriteModel toSave, final String email)
    {
        var customerData = createCustomerData(toSave.getCustomerData());

        DiscountCode discountCode = null;

        if (toSave.getDiscountCode() != null)
            discountCode = this.shopService.getDiscountCodeByName(toSave.getDiscountCode());

        var user = this.userService.getUserByEmail(email);

        var order =  this.orderRepo.save(new Order
                (
                        toSave.isInvoice()
                        , toSave.getPaymentMethod()
                        , toSave.getDeliveryMethod()
                        , toSave.isFourteenDays()
                        , toSave.isToCAccepted()
                        , toSave.getComment()
                        , discountCode
                        , customerData
                        , user
                ));

        assignShopItemsToOrder(this.shopService.getShopItemIdsByRefNumbers(toSave.getShopItems()), order.getId());

        return order;
    }

    private void assignShopItemsToOrder(final List<Integer> shopItemIds, final int orderId)
    {
        for (int id : shopItemIds)
            this.orderRepo.assignShopItemsToOrder(id, orderId);
    }

    List<Order> getOrdersByUserId(final int userId)
    {
        return this.orderRepo.findByUserId(userId);
    }

    List<OrderReadModel> getOrdersByUserIdAsReadModel(final int userId)
    {
        return getOrdersByUserId(userId)
                .stream()
                .map(Order::toReadModel)
                .toList();
    }
}
