package polskowniaApp.shop;

import org.springframework.stereotype.Service;
import polskowniaApp.shop.dto.ShopItemReadModel;

import java.util.List;

@Service
class ShopService
{
    private final ShopRepository shopRepo;

    ShopService(final ShopRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    List<ShopItem> getAll()
    {
        return this.shopRepo.findAll();
    }

    List<ShopItemReadModel> getAllAsReadModel()
    {
        return this.getAll()
                .stream()
                .map(ShopItem::toReadModel)
                .toList();
    }
}
