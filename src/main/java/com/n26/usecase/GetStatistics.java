package com.n26.usecase;

import com.n26.domain.Statistics;
import com.n26.domain.Transaction;
import com.n26.json.SummaryStatisticsDto;
import com.n26.repository.TransactionRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class GetStatistics {

  private final TransactionRepository transactionRepository;

  public GetStatistics(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public SummaryStatisticsDto execute() {
    final Collection<Transaction> transactions = transactionRepository.getAll();
    return new Statistics(transactions).getSummaryStatistics();
  }
}
