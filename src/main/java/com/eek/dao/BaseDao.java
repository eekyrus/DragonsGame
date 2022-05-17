package com.eek.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public class BaseDao {

  @Autowired
  @Qualifier("domRestTemplate")
  protected RestTemplate domRestTemplate;

}
