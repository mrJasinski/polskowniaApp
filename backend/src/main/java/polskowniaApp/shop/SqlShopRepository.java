package polskowniaApp.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlShopRepository extends ShopItemRepository, JpaRepository<ShopItem, Integer>
{

}
