package com.n26.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.n26.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTransactionTest {

  @InjectMocks
  private DeleteTransaction deleteTransaction;

  @Mock
  private TransactionRepository transactionRepository;

  @Test
  public void givenNothing_shouldDeleteAllTransactions() {
    deleteTransaction.execute();
    verify(transactionRepository, times(1)).deleteAll();
  }
}
