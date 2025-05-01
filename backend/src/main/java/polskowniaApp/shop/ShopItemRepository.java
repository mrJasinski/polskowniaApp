package polskowniaApp.shop;

import java.util.List;

interface ShopItemRepository
{
    List<ShopItem> findAll();

    ShopItem save(ShopItem toSave);

    boolean existsByTitle(String title);
}
