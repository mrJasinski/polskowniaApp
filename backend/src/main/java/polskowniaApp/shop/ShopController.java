package polskowniaApp.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShopController
{
    private final ShopService shopService;

    ShopController(final ShopService shopService)
    {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    ResponseEntity<?> getShop()
    {
        return ResponseEntity.ok(this.shopService.getAllShopItemsAsReadModel());
    }

    @GetMapping("/getShopItemCategories")
    ResponseEntity<?> getShopItemCategories()
    {
        return ResponseEntity.ok(this.shopService.getShopItemCategories());
    }
}
