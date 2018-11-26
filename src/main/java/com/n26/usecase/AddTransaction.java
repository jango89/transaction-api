package com.n26.usecase;

import com.n26.domain.Transaction;
import com.n26.exception.FutureTransactionException;
import com.n26.exception.OldTransactionException;
import com.n26.json.TransactionDto;
import com.n26.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class AddTransaction {

  private final TransactionRepository transactionRepository;

  public AddTransaction(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public void execute(TransactionDto transactionDto) {
    final Transaction transaction = new Transaction(transactionDto);
    validateTransactionDates(transaction);
    transactionRepository.add(transaction);
  }

  private void validateTransactionDates(Transaction transaction) {
    if (transaction.isOldTransaction()) {
      throw new OldTransactionException("Transaction time is older than 60 seconds");
    } else if (transaction.isFutureTransaction()) {
      throw new FutureTransactionException("Transaction time is in future");
    }
  }
}
