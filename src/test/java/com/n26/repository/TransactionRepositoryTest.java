package com.n26.repository;

import com.n26.domain.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionRepositoryTest {

  @Autowired
  private TransactionRepository transactionRepository;

  @Before
  public void setUp() {
    transactionRepository.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now()));
    transactionRepository.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now()));
  }

  @Test
  public void givenTransaction_shouldSave() {
    transactionRepository.add(new Transaction(new BigDecimal(10.00), LocalDateTime.now()));
  }

  @Test
  public void fetchAllTransactions() {
    Assert.assertTrue(transactionRepository.getAll().size() > 1);
  }
}