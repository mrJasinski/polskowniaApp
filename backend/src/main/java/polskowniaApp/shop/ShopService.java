package polskowniaApp.shop;

import org.springframework.stereotype.Service;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.shop.discount.DiscountCodeDTO;
import polskowniaApp.shop.discount.DiscountCodeRepository;
import polskowniaApp.shop.dto.ShopItemReadModel;
import polskowniaApp.shop.dto.ShopItemWriteModel;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
class ShopService
{
    private final ShopItemRepository shopItemRepo;
    private final DiscountCodeRepository discountCodeRepo;

    ShopService(final ShopItemRepository shopItemRepo, final DiscountCodeRepository discountCodeRepo)
    {
        this.shopItemRepo = shopItemRepo;
        this.discountCodeRepo = discountCodeRepo;
    }

    List<ShopItem> getAllShopItems()
    {
        return this.shopItemRepo.findAll();
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
        return this.shopItemRepo.save(new ShopItem(
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
        return this.shopItemRepo.findByRefNumber(refNumber)
                .orElseThrow(() -> new NoSuchElementException("Shop item with given ref number not found!"));
    }

    ShopItemReadModel getShopItemAsReadModelByRefNumber(final String refNumber)
    {
        return getShopItemByRefNumber(refNumber).toReadModel();
    }



    List<DiscountCode> getAllDiscountCodes()
    {
        return this.discountCodeRepo.findAll();
    }

    List<DiscountCodeDTO> getAllDiscountCodesAsDto()
    {
        return getAllDiscountCodes()
                .stream()
                .map(DiscountCode::toDto)
                .toList();
    }

    DiscountCode createDiscountCode(final DiscountCodeDTO code)
    {
//        sprawdzenie czy nie ma kodu o tej samej nazwie z zazębiającymi się datami

        return this.discountCodeRepo.save(new DiscountCode(
                code.getCode()
                , code.getValue()
                , code.getType()
                , code.getStartDate()
                , code.getEndDate()
        ));
    }

    double getDiscountValue(final String code, final double cartSum)
    {
        System.out.println("xxx");
        System.out.println(code);

        var discountCode = this.discountCodeRepo.findDiscountByNameAndDate(code)
                .orElseThrow(() -> new NoSuchElementException("Discount code not found!"));

        var discountValue = 0d;
        var codeValue = discountCode.getValue();

        switch (discountCode.getType())
        {
            case AMOUNT -> discountValue = codeValue;
            case PERCENT -> discountValue = (codeValue * cartSum) / 100;
        }

        return discountValue;
    }
}
