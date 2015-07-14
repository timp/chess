package toy.chess;

/**
 * @author timp
 * @since 2015-07-12.
 */
public class InvalidTurnException extends InvalidMoveException {
  public InvalidTurnException(String message) {
    super(message);
  }
}
