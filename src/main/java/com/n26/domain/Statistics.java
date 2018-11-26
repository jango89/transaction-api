package com.n26.domain;

import static java.math.BigDecimal.ROUND_HALF_UP;

import com.n26.json.SummaryStatisticsDto;
import java.math.BigDecimal;
import java.util.Collection;

public class Statistics {

  private long count = 0;
  private BigDecimal sum = bigDecimal(0.00);
  private BigDecimal max = bigDecimal(0.00);
  private BigDecimal min = bigDecimal(Double.MAX_VALUE);

  public Statistics(Collection<Transaction> transactions) {
    transactions.stream()
        .filter(transaction -> !transaction.isOldTransaction())
        .forEach(this::compute);
  }

  public SummaryStatisticsDto getSummaryStatistics() {
    BigDecimal average;
    if (containsTransactions()) {
      average = sum.divide(new BigDecimal(count), 2, ROUND_HALF_UP);
    } else {
      average = bigDecimal(0.00);
      min = bigDecimal(0.00);
    }
    return new SummaryStatisticsDto(count, sum, average, max, min);
  }

  private boolean containsTransactions() {
    return count > 0;
  }

  private BigDecimal bigDecimal(Double number) {
    return new BigDecimal(number)
        .setScale(2, ROUND_HALF_UP);
  }

  private void compute(Transaction transaction) {
    final BigDecimal roundUpAmount = transaction.retrieveRoundedAmount();
    count++;
    sum = sum.add(roundUpAmount);
    max = max.max(roundUpAmount);
    min = min.min(roundUpAmount);
  }

}
