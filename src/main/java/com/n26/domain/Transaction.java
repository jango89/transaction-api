package com.n26.domain;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.time.LocalDateTime.now;

import com.n26.json.TransactionDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

  private BigDecimal amount;
  private LocalDateTime time;

  public Transaction(BigDecimal amount, LocalDateTime time) {
    this.amount = amount;
    this.time = time;
  }

  public Transaction(TransactionDto transactionDto) {
    this(transactionDto.getAmount(), transactionDto.getTimestamp());
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public boolean isOldTransaction() {
    return time.isBefore(now().minusSeconds(60));
  }

  public BigDecimal retrieveRoundedAmount() {
    return amount.setScale(2, ROUND_HALF_UP);
  }

  public boolean isFutureTransaction() {
    return time.isAfter(now());
  }
}
