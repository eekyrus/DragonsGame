package com.eek.service;

import com.eek.dao.GameDao;
import com.eek.dto.TaskSolutionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameLoopService {

  @Autowired
  private GameDao gameDao;
  @Autowired
  private TaskService taskService;

  public long playGame(long targetScore) {
    long currentScore = 0;
    int currentLives = 3;

    String gameId = gameDao.startGame();
    while (currentScore < targetScore) {
      TaskSolutionResponse taskCompletionSummary = taskService.healAndPerformEasyTask(gameId, currentLives);
      currentLives = taskCompletionSummary.getLives();
      currentScore += taskCompletionSummary.getScore();
      log.info("Score: " + currentScore);
    }
    return currentScore;
  }

}
