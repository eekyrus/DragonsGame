package com.eek.service;

import com.eek.dao.ShopDao;
import com.eek.dao.TaskDao;
import com.eek.dto.TaskSolutionResponse;
import com.eek.dto.TaskDto;
import com.eek.model.TaskDifficulty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eek.model.ShopItem.*;

@Slf4j
@Service
public class TaskService {

  private static final int HEALING_THRESHOLD = 3;

  @Autowired
  private TaskDao taskDao;
  @Autowired
  private ShopDao shopDao;

  public List<TaskDto> fetchAllTasksSortedByDifficultyExpirationReward(String gameId) {
    TaskDto[] tasks = taskDao.fetchTasks(gameId);
    return Arrays.stream(tasks).sorted(
        Comparator.comparing((TaskDto t) -> TaskDifficulty.getByDescription(t.getProbability()).getDifficulty())
          .thenComparingInt(TaskDto::getExpiresIn)
          .thenComparing(TaskDto::getReward, Comparator.reverseOrder()))
      .collect(Collectors.toList());
  }

  public TaskSolutionResponse healAndPerformEasyTask(String gameId, int currentLives) {
    if (currentLives < HEALING_THRESHOLD) {
      shopDao.buyItem(gameId, HEALTH_POTION);
    }
    List<TaskDto> tasksByDifficultyAndExpiration = fetchAllTasksSortedByDifficultyExpirationReward(gameId);
    TaskDto pickedTask = tasksByDifficultyAndExpiration.get(0);
    return taskDao.solveTask(gameId, pickedTask.getAdId());
  }

}
