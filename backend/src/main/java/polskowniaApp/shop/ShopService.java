package polskowniaApp.shop;

import org.springframework.stereotype.Service;
import polskowniaApp.shop.dto.ShopItemReadModel;

import java.util.Arrays;
import java.util.List;

@Service
class ShopService
{
    private final ShopItemRepository shopRepo;

    ShopService(final ShopItemRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    List<ShopItem> getAllShopItems()
    {
        return this.shopRepo.findAll();
    }

    List<ShopItemReadModel> getAllShopItemsAsReadModel()
    {
        return this.getAllShopItems()
                .stream()
                .map(ShopItem::toReadModel)
                .toList();
    }

    List<String> getShopItemCategories()
    {
        return Arrays.stream(ShopItem.Category.values())
                .map(ShopItem.Category::getName)
                .toList();
    }
}
