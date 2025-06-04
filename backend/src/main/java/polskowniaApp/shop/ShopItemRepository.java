package polskowniaApp.shop;

import java.util.List;
import java.util.Optional;

interface ShopItemRepository
{
    List<ShopItem> findAll();

    ShopItem save(ShopItem toSave);

    boolean existsByTitle(String title);

    Optional<ShopItem> findByRefNumber(String refNumber);

    List<Integer> findByRefNumbers(List<String> itemRefNumbers);

    List<ShopItem> findByIds(List<Integer> itemIds);
}
