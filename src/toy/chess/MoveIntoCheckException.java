package toy.chess;

/**
 * @author timp
 * @since 2015-07-07.
 */
public class MoveIntoCheckException extends InvalidMoveException {
  public MoveIntoCheckException(String message) {
    super(message);
  }
}
