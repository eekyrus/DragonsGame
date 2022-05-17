package com.eek.service;

import com.eek.dao.GameDao;
import com.eek.dto.TaskSolutionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameLoopServiceTest {

  private static final String GAME_ID = "12345";
  private static final long TARGET_SCORE = 10000;
  private static final long EXPECTED_SCORE = 10020;

  @Mock
  private GameDao gameDao;
  @Mock
  private TaskService taskService;
  @InjectMocks
  private GameLoopService gameLoopService;

  @Test
  void playGame() {
    when(gameDao.startGame()).thenReturn(GAME_ID);
    when(taskService.healAndPerformEasyTask(eq(GAME_ID), anyInt()))
      .thenReturn(new TaskSolutionResponse(true, 3, 50, 60, 0, 0, "Task solved."));

    long score = gameLoopService.playGame(TARGET_SCORE);

    assertEquals(EXPECTED_SCORE, score);
  }

}