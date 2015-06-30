package toy.chess;

public class InvalidMoveException extends RuntimeException {
  public InvalidMoveException(String message ) {
    super(message);
  }
}
