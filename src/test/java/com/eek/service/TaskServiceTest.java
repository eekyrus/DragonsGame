package com.eek.service;

import com.eek.dao.ShopDao;
import com.eek.dao.TaskDao;
import com.eek.dto.TaskDto;
import com.eek.model.TaskDifficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.eek.model.ShopItem.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

  private static final String GAME_ID = "12345";
  private static final int LIVES_LESS_THAN_MAX = 2;
  private static final int LIVES_MAX = 3;

  @Mock
  private TaskDao taskDao;
  @Mock
  private ShopDao shopDao;
  @InjectMocks
  private TaskService taskService;

  @BeforeEach
  public void setup() {
    when(taskDao.fetchTasks(eq(GAME_ID))).thenReturn(mockTasks());
  }

  @Test
  void fetchAllTasksSortedByDifficultyExpirationReward() {
    List<TaskDto> sortedTasks = taskService.fetchAllTasksSortedByDifficultyExpirationReward(GAME_ID);

    assertArrayEquals(createExpectedSortedTasks(), sortedTasks.toArray());
  }

  @Test
  void healAndPerformEasyTask() {
    when(shopDao.buyItem(eq(GAME_ID), eq(HEALTH_POTION))).thenReturn(null);
    when(taskDao.solveTask(eq(GAME_ID), anyString())).thenReturn(null);

    taskService.healAndPerformEasyTask(GAME_ID, LIVES_MAX);

    verifyNoInteractions(shopDao);

    taskService.healAndPerformEasyTask(GAME_ID, LIVES_LESS_THAN_MAX);

    verify(shopDao).buyItem(eq(GAME_ID), eq(HEALTH_POTION));
  }

  private TaskDto[] mockTasks() {
    return new TaskDto[]{
      new TaskDto("3", "Task Three", 30, 5, TaskDifficulty.RATHER_DETRIMENTAL.getDescription()),
      new TaskDto("1", "Task One", 10, 5, TaskDifficulty.PIECE_OF_CAKE.getDescription()),
      new TaskDto("2", "Task Two", 20, 4, TaskDifficulty.PIECE_OF_CAKE.getDescription()),
      new TaskDto("4", "Task Four", 40, 5, TaskDifficulty.SURE_THING.getDescription()),
      new TaskDto("5", "Task Five", 50, 2, TaskDifficulty.SURE_THING.getDescription()),
      new TaskDto("6", "Task Six", 60, 2, TaskDifficulty.SURE_THING.getDescription())
    };
  }

  private TaskDto[] createExpectedSortedTasks() {
    return new TaskDto[]{
      new TaskDto("6", "Task Six", 60, 2, TaskDifficulty.SURE_THING.getDescription()),
      new TaskDto("5", "Task Five", 50, 2, TaskDifficulty.SURE_THING.getDescription()),
      new TaskDto("4", "Task Four", 40, 5, TaskDifficulty.SURE_THING.getDescription()),
      new TaskDto("2", "Task Two", 20, 4, TaskDifficulty.PIECE_OF_CAKE.getDescription()),
      new TaskDto("1", "Task One", 10, 5, TaskDifficulty.PIECE_OF_CAKE.getDescription()),
      new TaskDto("3", "Task Three", 30, 5, TaskDifficulty.RATHER_DETRIMENTAL.getDescription())
    };
  }

}