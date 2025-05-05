package polskowniaApp.shop;

import org.springframework.stereotype.Service;
import polskowniaApp.shop.dto.ShopItemReadModel;
import polskowniaApp.shop.dto.ShopItemWriteModel;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
        var result = new ArrayList<String>();

        result.add("Wszystkie");
        result.addAll(
                Arrays.stream(Category.values())
                .map(Category::getName)
                .toList());
        
        return result;
    }

    ShopItem createShopItem(final ShopItemWriteModel item)
    {
        return this.shopRepo.save(new ShopItem(
                item.getTitle()
                , item.getPrice()
                , item.getDescription()
                , Category.getByName(item.getCategory())
                , item.getLength()
                , item.getDuration()
                , Level.valueOf(item.getLevel())
        ));
    }

    ShopItem getShopItemByRefNumber(final String refNumber)
    {
        return this.shopRepo.findByRefNumber(refNumber)
                .orElseThrow(() -> new NoSuchElementException("Shop item with given ref number not found!"));
    }

    ShopItemReadModel getShopItemAsReadModelByRefNumber(final String refNumber)
    {
        return getShopItemByRefNumber(refNumber).toReadModel();
    }
}
