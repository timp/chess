package com.megabuyte;

public class InvalidMoveException extends RuntimeException {
  public InvalidMoveException(String message ) {
    super(message);
  }
}
