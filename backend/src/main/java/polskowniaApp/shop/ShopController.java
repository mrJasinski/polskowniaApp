package polskowniaApp.shop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import polskowniaApp.fileManager.FileManagerService;
import polskowniaApp.shop.discount.CodeDTO;
import polskowniaApp.shop.discount.DiscountCodeDTO;
import polskowniaApp.shop.dto.ShopItemWriteModel;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
class ShopController
{
    private final ShopService shopService;
    private final FileManagerService fileService;

    ShopController(final ShopService shopService, final FileManagerService fileService)
    {
        this.shopService = shopService;
        this.fileService = fileService;
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
    ResponseEntity<?> addShopItem(
            @RequestPart(value = "item") String item
            , @RequestPart(value = "uploadedFile") MultipartFile uploadedFile
            , @RequestPart(value = "uploadedLogo") MultipartFile uploadedLogo)
    {
//        TODO - w przypadku uaktualnienia odczyt obiektu z bazy np poprzerz refNumber i uaktualnienie pól

        var mapper = new ObjectMapper();
        ShopItemWriteModel dto = null;

        try
        {
            dto = mapper.readValue(item, ShopItemWriteModel.class);
        } catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }

        this.shopService.createShopItemWithFiles(dto, uploadedFile.getOriginalFilename(), uploadedLogo.getOriginalFilename());

        this.fileService.saveFile(uploadedFile);
        this.fileService.saveFile(uploadedLogo);

        return ResponseEntity.ok().body("Pozycja sklepu utworzona!");
    }

//    @PostMapping("/addShopItem")
//    ResponseEntity<?> addShopItem(@RequestBody ShopItemWriteModel item)
//    {
////        TODO - w przypadku uaktualnienia odczyt obiektu z bazy np poprzerz refNumber i uaktualnienie pól
//
//        this.shopService.createShopItem(item);
//
//        return ResponseEntity.ok().body("Pozycja sklepu utworzona!");
//    }

    @GetMapping("/shopItem/{refNumber}")
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
