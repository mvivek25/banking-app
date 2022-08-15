package com.demo.banking.exception;

/** This is a ServiceException thrown when there is a service error */
public class ServiceException extends RuntimeException {
  public ServiceException(String errorMessage) {
    super(errorMessage);
  }
}
