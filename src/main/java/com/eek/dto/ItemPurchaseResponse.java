package com.eek.dto;

import lombok.Data;

@Data
public class ItemPurchaseResponse {

  private String shoppingSuccess;
  private long gold;
  private int lives;
  private int level;
  private long turn;

}
