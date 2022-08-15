package com.demo.banking.exception;

/** This is a DataAccessException thrown when there is a DataAccess error */
public class DataAccessException extends RuntimeException {
  public DataAccessException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
