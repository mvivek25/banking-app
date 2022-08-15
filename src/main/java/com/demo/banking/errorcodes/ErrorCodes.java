package com.demo.banking.errorcodes;

/** Enum holding the error codes and descriptions */
public enum ErrorCodes {
  DATA_ACCESS(0001, "Unable to retrieve data from open API"),
  INVALID_RESPONSE(0002, "Invalid Response received"),
  NO_ATMS_DATA(0003, "No Atms Data available");

  private final int code;
  private final String description;

  private ErrorCodes(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public int getCode() {
    return code;
  }
}
