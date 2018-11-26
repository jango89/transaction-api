package com.n26.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.n26.exception.FutureTransactionException;
import com.n26.exception.OldTransactionException;
import com.n26.json.ErrorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  private Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

  @ResponseStatus(NO_CONTENT)
  @ExceptionHandler({OldTransactionException.class})
  public ErrorData oldTransaction(OldTransactionException exp) {
    return new ErrorData(exp.getMessage(), 2000);
  }

  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler({FutureTransactionException.class})
  public ErrorData futureTransaction(FutureTransactionException exp) {
    return new ErrorData(exp.getMessage(), 2001);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({MismatchedInputException.class, MethodArgumentNotValidException.class})
  public ErrorData handleMethodArgumentNotValid(Exception ex) {
    log.error("Json not valid", ex);
    return new ErrorData("Json not valid", 4000);
  }

  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ErrorData handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    final ErrorData errorData = new ErrorData(
        "The http request was not readable, please check your request", 3000);

    log.error("Http message not readable", ex);
    return errorData;
  }

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler({RuntimeException.class})
  public ErrorData generalError(RuntimeException exp) {
    log.error("General Error occurred", exp);
    return new ErrorData("Internal Server Error", 1000);
  }
}
