package com.n26.exception;


public class FutureTransactionException extends RuntimeException {

  public FutureTransactionException(String message) {
    super(message);
  }
}
