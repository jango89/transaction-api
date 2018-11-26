package com.n26.usecase;

import com.n26.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTransaction {

  private final TransactionRepository transactionRepository;

  public DeleteTransaction(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public void execute() {
    transactionRepository.deleteAll();
  }
}
