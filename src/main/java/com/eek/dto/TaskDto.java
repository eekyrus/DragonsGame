package com.eek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

  private String adId;
  private String message;
  private long reward;
  private Integer expiresIn;
  private String probability;

}
