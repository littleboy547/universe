package com.uvs.exception;

public class UvsException extends Exception {

  private static final long serialVersionUID = 1L;

  public UvsException() {
    super();
  }

  public UvsException(String message) {
    super(message);
  }

  public UvsException(Throwable cause) {
    super(cause);
  }

  public UvsException(String message, Throwable cause) {
    super(message, cause);
  }

}
