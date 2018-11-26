package com.n26.usecase;

import static org.junit.Assert.assertNotNull;

import com.n26.exception.FutureTransactionException;
import com.n26.exception.OldTransactionException;
import com.n26.json.TransactionDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddTransactionTest {

  @Autowired
  private AddTransaction addTransaction;

  @Test
  public void givenTransactionTimeAndAmount_shouldSaveTransaction() {
    TransactionDto transactionDto = new TransactionDto(new BigDecimal(10.00), LocalDateTime.now());
    addTransaction.execute(transactionDto);
    assertNotNull(transactionDto);
  }

  @Test(expected = OldTransactionException.class)
  public void givenTransactionAmountAndTimeLessThanSixtySecondsFromCurrentTime_shouldFailTransaction() {
    TransactionDto transactionDto = new TransactionDto(new BigDecimal(10.00),
        LocalDateTime.now().minusMinutes(2));
    addTransaction.execute(transactionDto);
  }

  @Test
  public void givenTransactionAmountAndTimeExactlyLessThanSixtySecondsFromCurrentTime_shouldCreateTransaction() {
    TransactionDto transactionDto = new TransactionDto(new BigDecimal(10.00),
        LocalDateTime.now().minusSeconds(60));
    addTransaction.execute(transactionDto);
    assertNotNull(transactionDto);
  }

  @Test(expected = FutureTransactionException.class)
  public void givenTransactionAmountAndTimeInFuture_shouldFailTransaction() {
    TransactionDto transactionDto = new TransactionDto(new BigDecimal(10.00),
        LocalDateTime.now().plusMinutes(2));
    addTransaction.execute(transactionDto);
  }

  @Test
  public void givenTransactionAmountAndTimeExactlyLikeCurrentTime_shouldCreateTransaction() {
    TransactionDto transactionDto = new TransactionDto(new BigDecimal(10.00),
        LocalDateTime.now());
    addTransaction.execute(transactionDto);
    assertNotNull(transactionDto);
  }
}
