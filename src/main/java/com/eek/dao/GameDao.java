package com.eek.dao;

import com.eek.dto.GameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GameDao extends BaseDao {

  public String startGame() {
    String gameId = domRestTemplate.postForObject("game/start", null, GameDto.class).getGameId();
    log.info(gameId);
    return gameId;
  }

}
