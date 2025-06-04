package polskowniaApp.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlShopRepository extends ShopItemRepository, JpaRepository<ShopItem, Integer>
{
    @Override
    @Query(value = "SELECT id FROM shop_items WHERE ref_number IN :itemRefNumbers", nativeQuery = true)
    List<Integer> findByRefNumbers(List<String> itemRefNumbers);

    @Override
    @Query(value = "SELECT * FROM shop_items WHERE id IN :itemIds", nativeQuery = true)
    List<ShopItem> findByIds(List<Integer> itemIds);
}
