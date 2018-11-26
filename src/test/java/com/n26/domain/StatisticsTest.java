package com.n26.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.n26.json.SummaryStatisticsDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StatisticsTest {

  @Test
  public void shouldReturnStatistics_whenAskedForResults() {
    Statistics statistics = new Statistics(Collections.EMPTY_LIST);
    final SummaryStatisticsDto summaryStatistics = statistics.getSummaryStatistics();
    assertNotNull(summaryStatistics);
    assertEquals(summaryStatistics.getCount(), 0L);
  }

  @Test
  public void shouldReturnStatisticsForLastSixtySeconds_whenAskedForResults() {
    final List<Transaction> transactionList = createTransactionList();
    transactionList.addAll(createTransactionListOlderThanNow());
    final SummaryStatisticsDto summaryStatistics = new Statistics(transactionList)
        .getSummaryStatistics();

    assertNotNull(summaryStatistics);
    assertEquals(summaryStatistics.getCount(), 3L);
    assertEquals(summaryStatistics.getSum(), "20.00");
    assertEquals(summaryStatistics.getMax(), "10.00");
    assertEquals(summaryStatistics.getMin(), "5.00");
    assertEquals(summaryStatistics.getAvg(), "6.67");
  }

  private List<Transaction> createTransactionList() {
    List<Transaction> transactions = new LinkedList<>();
    transactions.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now()));
    transactions.add(new Transaction(new BigDecimal(5), LocalDateTime.now()));
    transactions.add(new Transaction(new BigDecimal(5.0), LocalDateTime.now()));
    return transactions;
  }

  private List<Transaction> createTransactionListOlderThanNow() {
    List<Transaction> transactions = new LinkedList<>();
    transactions.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now().minusMinutes(2)));
    transactions.add(new Transaction(new BigDecimal(5), LocalDateTime.now().minusMinutes(2)));
    transactions.add(new Transaction(new BigDecimal(5.0), LocalDateTime.now().minusMinutes(2)));
    return transactions;
  }

}
