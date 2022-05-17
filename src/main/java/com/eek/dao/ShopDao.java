package com.eek.dao;

import com.eek.dto.ItemPurchaseResponse;
import com.eek.dto.ShopItemDto;
import com.eek.model.ShopItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.net.URI;

@Slf4j
@Repository
public class ShopDao extends BaseDao {

  public ItemPurchaseResponse buyItem(String gameId, ShopItem item) {
    URI uri = domRestTemplate.getUriTemplateHandler().expand("{gameId}/shop/buy/{itemId}", gameId, item.getCode());
    ItemPurchaseResponse response = domRestTemplate.postForObject(uri, null, ItemPurchaseResponse.class);
    log.info("Purchasing " + item + ", purchase status: " + response.getShoppingSuccess());
    return response;
  }

  public ShopItemDto[] fetchShopInventory(String gameId) {
    URI uri = domRestTemplate.getUriTemplateHandler().expand("{gameId}/shop", gameId);
    return domRestTemplate.getForObject(uri, ShopItemDto[].class);
  }

}
