package polskowniaApp.shop;

import java.util.List;

interface ShopRepository
{
    List<ShopItem> findAll();

    ShopItem save(ShopItem toSave);
}
