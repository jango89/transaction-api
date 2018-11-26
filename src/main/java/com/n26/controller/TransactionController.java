package com.n26.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.n26.json.TransactionDto;
import com.n26.usecase.AddTransaction;
import com.n26.usecase.DeleteTransaction;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final AddTransaction addTransaction;
  private final DeleteTransaction deleteTransaction;

  public TransactionController(AddTransaction addTransaction,
      DeleteTransaction deleteTransaction) {
    this.addTransaction = addTransaction;
    this.deleteTransaction = deleteTransaction;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public void addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
    addTransaction.execute(transactionDto);
  }

  @DeleteMapping
  @ResponseStatus(NO_CONTENT)
  public void deleteTransction() {
    deleteTransaction.execute();
  }
}
