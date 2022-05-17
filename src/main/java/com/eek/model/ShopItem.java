package com.eek.model;

import lombok.Getter;

@Getter
public enum ShopItem {

  HEALTH_POTION("hpot");

  private String code;

  ShopItem(String code) {
    this.code = code;
  }
}
