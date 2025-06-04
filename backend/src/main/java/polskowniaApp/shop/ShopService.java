package polskowniaApp.shop;

import org.springframework.stereotype.Service;
import polskowniaApp.shop.discount.DiscountCode;
import polskowniaApp.shop.discount.DiscountCodeDTO;
import polskowniaApp.shop.discount.DiscountCodeRepository;
import polskowniaApp.shop.dto.ShopItemReadModel;
import polskowniaApp.shop.dto.ShopItemWriteModel;
import polskowniaApp.utils.Category;
import polskowniaApp.utils.Level;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static polskowniaApp.fileManager.FileManagerService.STORAGE_DIRECTORY;

@Service
public class ShopService
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
//        TODO
//        jeśli zmienię zapis do bazy z poziomami jako listą to wtedy pozycje sklepu trzeba dodatkowo iterować po poziomach

        return this.getAllShopItems()
                .stream()
                .map(ShopItem::toReadModel)
                .toList();
    }

    public DiscountCode getDiscountCodeByName(final String code)
    {
        return this.discountCodeRepo.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Discount code with given name not found!"));
    }

    List<String> getShopItemCategories()
    {

        return new ArrayList<String>(Arrays.stream(Category.values())
                .map(Category::getName)
                .toList());
    }

//    ShopItem createShopItem(final ShopItemWriteModel item)
//    {
//        return this.shopItemRepo.save(new ShopItem(
//                item.getTitle()
//                , item.getPrice()
//                , item.getDescription()
//                , Category.getByName(item.getCategory())
//                , item.getLength()
//                , item.getDuration()
//                , Level.valueOf(item.getLevel())
//        ));
//    }
//
//    ShopItem createShopItemWithFile(final ShopItemWriteModel item, final String fileReference)
//    {
//        return this.shopItemRepo.save(new ShopItem(
//                item.getTitle()
//                , item.getPrice()
//                , item.getDescription()
//                , Category.getByName(item.getCategory())
//                , item.getLength()
//                , item.getDuration()
//                , Level.valueOf(item.getLevel())
//                , fileReference
//        ));
//    }

    ShopItem createShopItemWithFiles(final ShopItemWriteModel item, final String fileName, final String logoName)
    {
        var storage = STORAGE_DIRECTORY + File.separator;

        return this.shopItemRepo.save(new ShopItem(
                item.getTitle()
                , item.getPrice()
                , item.getDescription()
                , Category.getByName(item.getCategory())
                , item.getLength()
                , item.getDuration()
                , Level.valueOf(item.getLevel())
                , storage + fileName
                , storage + logoName
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

    public List<Integer> getShopItemIdsByRefNumbers(final List<String> itemRefNumbers)
    {
        return this.shopItemRepo.findByRefNumbers(itemRefNumbers);
    }

    public List<ShopItem> getShopItemsByIds(final List<Integer> itemIds)
    {
        return this.shopItemRepo.findByIds(itemIds);
    }
}
