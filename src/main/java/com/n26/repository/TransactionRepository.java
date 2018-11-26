package com.n26.repository;

import com.n26.domain.Transaction;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

  private AtomicInteger atomicInteger = new AtomicInteger(0);
  private ConcurrentHashMap<Integer, Transaction> transactions = new ConcurrentHashMap<>();

  public void add(final Transaction transaction) {
    transactions.put(atomicInteger.incrementAndGet(), transaction);
  }

  public Collection<Transaction> getAll() {
    return transactions.values();
  }

  public void deleteAll() {
    transactions.clear();
  }
}
