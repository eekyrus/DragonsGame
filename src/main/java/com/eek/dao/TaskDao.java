package com.eek.dao;

import com.eek.dto.TaskDto;
import com.eek.dto.TaskSolutionResponse;
import com.eek.model.TaskDifficulty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class TaskDao extends BaseDao {

  public TaskDto[] fetchTasks(String gameId) {
    URI uri = domRestTemplate.getUriTemplateHandler().expand("{gameId}/messages", gameId);
    return domRestTemplate.getForObject(uri, TaskDto[].class);
  }

  public Map<TaskDifficulty, List<TaskDto>> fetchAllTasksGroupedByDifficulty(String gameId) {
    TaskDto[] tasks = fetchTasks(gameId);
    return Arrays.stream(tasks).collect(Collectors.groupingBy(task -> TaskDifficulty.getByDescription(task.getProbability())));
  }

  public TaskSolutionResponse solveTask(String gameId, String adId) {
    URI uri = domRestTemplate.getUriTemplateHandler().expand("{gameId}/solve/{adId}", gameId, adId);
    TaskSolutionResponse response = domRestTemplate.postForObject(uri, null, TaskSolutionResponse.class);
    log.info("Hero goes on a quest.. Quest status: " + response.isSuccess());
    return response;
  }

}
