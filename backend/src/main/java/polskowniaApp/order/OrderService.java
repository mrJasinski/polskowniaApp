package polskowniaApp.order;

import org.springframework.stereotype.Service;
import polskowniaApp.fileManager.FileWrapper;
import polskowniaApp.order.dto.OrderReadModel;
import polskowniaApp.order.dto.OrderWriteModel;
import polskowniaApp.shop.ShopService;
import polskowniaApp.shop.ShopItem;

import polskowniaApp.user.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
class OrderService
{
    private final OrderRepository orderRepo;
    private final ShopService shopService;
    private final UserService userService;

    OrderService(OrderRepository orderRepo, final ShopService shopService, final UserService userService)
    {
        this.orderRepo = orderRepo;
        this.shopService = shopService;
        this.userService = userService;
    }
//    zakomentowane pod test - zmiana w kodzie customer data wyniesione do user i nie bedzie tworzone nowe przy zamówieniu tylko odczyt zapisanego
   Order createOrder(final OrderWriteModel toSave, final String email)
   {
//        var customerData = createCustomerData(toSave.getCustomerData());
//
//        DiscountCode discountCode = null;
//
//        if (toSave.getDiscountCode() != null)
//            discountCode = this.shopService.getDiscountCodeByName(toSave.getDiscountCode());
//
//        var user = this.userService.getUserByEmail(email);
//
//        var order =  this.orderRepo.save(new Order
//                (
//                        toSave.isInvoice()
//                        , toSave.getPaymentMethod()
//                        , toSave.getDeliveryMethod()
//                        , toSave.isFourteenDays()
//                        , toSave.isToCAccepted()
//                        , toSave.getComment()
//                        , discountCode
//                        , customerData
//                        , user
//                ));
//
//        assignShopItemsToOrder(this.shopService.getShopItemIdsByRefNumbers(toSave.getShopItems()), order.getId());
//
//        return order;
       return null;
    }

    private void assignShopItemsToOrder(final List<Integer> shopItemIds, final int orderId)
    {
        for (int itemId : shopItemIds)
            this.orderRepo.assignShopItemToOrder(itemId, orderId);
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

    List<FileWrapper> getMyShelfByUserId(final int userId)
    {
        var result = new ArrayList<FileWrapper>();

//        po user id wyciągam zamówienia
        var orderIds = this.orderRepo.findOrderIdsByUserId(userId);

        var itemIds = this.orderRepo.findShopItemsIdsByOrderIds(orderIds);

        var items = this.shopService.getShopItemsByIds(itemIds);

        var references = items.stream().filter(i -> i.getFileReference() != null).map(ShopItem::getFileReference).toList();

//        po zamówieniach itemy które mają plik i te wrappować

        for (String r : references)
        {
            var f = new File(r);
            result.add(new FileWrapper(f.getName()));
        }

        return result;
    }

    List<Order> getAllOrders()
    {
        return this.orderRepo.findAll();
    }

    List<OrderReadModel> getAllOrdersAsReadModel()
    {
        return getAllOrders()
                .stream()
                .map(Order::toReadModel)
                .toList();
    }


    OrderReadModel getOrderByRefNumberAsReadModel(final String refNumber)
    {
        var order = getOrderByRefNumber(refNumber);

        var itemIds = this.orderRepo.findOrderedItemsIdsByOrderId(order.getId());

        var items = this.shopService.getShopItemsByIdsAsReadModel(itemIds);

        var result = order.toReadModel();

        result.setItems(items);

        return result;
    }

    private Order getOrderByRefNumber(final String refNumber)
    {
        return this.orderRepo.findByRefNumber(refNumber)
                .orElseThrow(() -> new NoSuchElementException("Order with given ref number not found!"));
    }
}
