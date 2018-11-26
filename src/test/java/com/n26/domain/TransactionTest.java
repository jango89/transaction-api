package com.n26.domain;


import static java.math.BigDecimal.ROUND_HALF_UP;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TransactionTest {

  @Test
  public void givenAmountAndTransactionTime_shouldCreateTransaction() {
    Transaction transaction = new Transaction(new BigDecimal(10.00), LocalDateTime.now());
    assertNotNull(transaction);
    assertEquals(transaction.getAmount().floatValue(), 10.00f);
  }

  @Test
  public void givenAmountAndTransactionTimeLessThanSixtyOneSecondsFromCurrentTime_shouldReturnOlderTransactionStatusTrue() {
    boolean isOlderTransaction = new Transaction(new BigDecimal(10.00),
        subtractSecondsFromCurrentLocalDateTime(61))
        .isOldTransaction();

    assertTrue(isOlderTransaction);
  }

  @Test
  public void givenAmountAndTransactionTimeExactlyLessThanSixtySecondsFromCurrentTime_shouldReturnOlderTransactionStatusFalse() {
    boolean isOlderTransaction = new Transaction(new BigDecimal(10.00),
        subtractSecondsFromCurrentLocalDateTime(60))
        .isOldTransaction();

    assertFalse(isOlderTransaction);
  }

  @Test
  public void givenAmountAndTransactionTimeLessThanTwentySecondsFromCurrentTime_shouldReturnOlderTransactionStatusFalse() {
    boolean isOlderTransaction = new Transaction(new BigDecimal(10.00),
        subtractSecondsFromCurrentLocalDateTime(20))
        .isOldTransaction();

    assertFalse(isOlderTransaction);
  }

  @Test
  public void givenAmountAndTransactionTimeMoreThanTwentySecondsFromCurrentTime_shouldReturnFutureTransactionStatusTrue() {
    boolean isFutureTransaction = new Transaction(new BigDecimal(10.00),
        subtractSecondsFromCurrentLocalDateTime(-20))
        .isFutureTransaction();

    assertTrue(isFutureTransaction);
  }

  @Test
  public void givenAmountAndTransactionTimeExactlyCurrentTime_shouldReturnFutureTransactionStatusFalse() {
    boolean isFutureTransaction = new Transaction(new BigDecimal(10.00),
        subtractSecondsFromCurrentLocalDateTime(0))
        .isFutureTransaction();

    assertFalse(isFutureTransaction);
  }

  @Test
  public void givenTransactionAmountWithOneDecimal_shouldRoundUpHafAndReturnInDouble() {
    Transaction transaction = new Transaction(new BigDecimal(10.0), LocalDateTime.now());
    assertNotNull(transaction);
    assertEquals(transaction.retrieveRoundedAmount(),
        new BigDecimal(10.00).setScale(2, ROUND_HALF_UP));
  }

  @Test
  public void givenTransactionAmountWithThreeDecimals_shouldRoundUpHafAndReturnInDouble() {
    Transaction transaction = new Transaction(new BigDecimal(10.010), LocalDateTime.now());
    assertNotNull(transaction);
    assertEquals(transaction.retrieveRoundedAmount(), new BigDecimal(10.01).setScale(2, ROUND_HALF_UP));
  }

  @Test
  public void givenTransactionAmountWithTwoDecimals_shouldRoundUpHafAndReturnInDouble() {
    Transaction transaction = new Transaction(new BigDecimal(10.01), LocalDateTime.now());
    assertNotNull(transaction);
    assertEquals(transaction.retrieveRoundedAmount(), new BigDecimal(10.01).setScale(2, ROUND_HALF_UP));
  }

  private LocalDateTime subtractSecondsFromCurrentLocalDateTime(long seconds) {
    return LocalDateTime.now()
        .minusSeconds(seconds);
  }
}
