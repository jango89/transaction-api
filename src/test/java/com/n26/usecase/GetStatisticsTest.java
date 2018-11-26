package com.n26.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.n26.domain.Transaction;
import com.n26.json.SummaryStatisticsDto;
import com.n26.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetStatisticsTest {

  @InjectMocks
  private GetStatistics getStatistics;

  @Mock
  private TransactionRepository transactionRepository;

  @Test
  public void shouldFetchStatistics_whenTransactionTimeIsLessThanSixtySeconds() {
    when(transactionRepository.getAll()).thenReturn(getTransactions());
    final SummaryStatisticsDto summaryStatisticsDto = getStatistics.execute();
    assertNotNull(summaryStatisticsDto);
    assertEquals(summaryStatisticsDto.getCount(), 1);
  }

  private Collection<Transaction> getTransactions() {
    List<Transaction> transactions = new LinkedList<>();
    transactions.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now()));
    return transactions;
  }
}
