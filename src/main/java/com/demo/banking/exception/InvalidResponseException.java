package com.demo.banking.exception;

/** This is a InvalidResponseException thrown when there is an Invalid Response */
public class InvalidResponseException extends RuntimeException {

  public InvalidResponseException(String errorMessage) {
    super(errorMessage);
  }
}
