package polskowniaApp.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polskowniaApp.shop.dto.ShopItemWriteModel;

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

    @PostMapping("/addShopItem")
    ResponseEntity<?> addShopItem(@RequestBody ShopItemWriteModel item)
    {
//        TODO - w przypadku uaktualnienia odczyt obiektu z bazy np poprzerz refNumber i uaktualnienie pól

        this.shopService.createShopItem(item);

        return ResponseEntity.ok("Pozycja sklepu utworzona!");
    }

    @GetMapping("/getShopItem/{refNumber}")
    ResponseEntity<?> getShopItem(@PathVariable String refNumber)
    {
        return ResponseEntity.ok(this.shopService.getShopItemAsReadModelByRefNumber(refNumber));
    }
}
