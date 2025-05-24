package polskowniaApp.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polskowniaApp.shop.discount.CodeDTO;
import polskowniaApp.shop.discount.DiscountCodeDTO;
import polskowniaApp.shop.dto.ShopItemWriteModel;

import java.net.URI;
import java.util.NoSuchElementException;

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

    @GetMapping("getDiscountCodes")
    ResponseEntity<?> getDiscountCodes()
    {
        return ResponseEntity.ok(this.shopService.getAllDiscountCodesAsDto());
    }

    @PostMapping("/generateDiscountCode")
    ResponseEntity<?> generateDiscountCode(@RequestBody DiscountCodeDTO code)
    {
        var result = this.shopService.createDiscountCode(code);

        return ResponseEntity.created(URI.create("/" + result.getCode())).body(result);
    }

    @PostMapping("/getDiscount")
    ResponseEntity<?> getDiscount(@RequestBody CodeDTO json)
    {
        try
        {
            return ResponseEntity.ok(this.shopService.getDiscountValue(json.getDiscountCode(), json.getCartSum()));
        }
        catch (NoSuchElementException ex)
        {
            return ResponseEntity.badRequest().body("Podany kod rabatowy nie istnieje!");
        }
    }
}
