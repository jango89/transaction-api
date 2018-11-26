package com.n26.json;

import java.math.BigDecimal;

public class SummaryStatisticsDto {

  private String sum;
  private String avg;
  private String max;
  private String min;
  private long count;

  public SummaryStatisticsDto(long count, BigDecimal sum, BigDecimal avg,
      BigDecimal max, BigDecimal min) {
    this.count = count;
    this.sum = sum.toString();
    this.avg = avg.toString();
    this.max = max.toString();
    this.min = min.toString();
  }

  public String getSum() {
    return sum;
  }

  public String getAvg() {
    return avg;
  }

  public String getMax() {
    return max;
  }

  public String getMin() {
    return min;
  }

  public long getCount() {
    return count;
  }
}
