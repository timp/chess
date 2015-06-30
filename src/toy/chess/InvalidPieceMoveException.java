package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class InvalidPieceMoveException extends InvalidMoveException {
  public InvalidPieceMoveException(String message) {
    super(message);
  }
}
