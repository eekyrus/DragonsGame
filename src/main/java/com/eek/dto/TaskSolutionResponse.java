package com.eek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSolutionResponse {

  private boolean success;
  private int lives;
  private long gold;
  private long score;
  private long highScore;
  private int turn;
  private String message;

}
