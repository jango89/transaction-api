package com.n26.controller;

import static org.springframework.http.HttpStatus.OK;

import com.n26.json.SummaryStatisticsDto;
import com.n26.usecase.GetStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

  private final GetStatistics getStatistics;

  public StatisticsController(GetStatistics getStatistics) {
    this.getStatistics = getStatistics;
  }

  @GetMapping
  @ResponseStatus(OK)
  public SummaryStatisticsDto getStatistics() {
    return getStatistics.execute();
  }
}
