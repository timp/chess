package toy.chess;

/**
 * @author timp
 * @since 2015-07-07.
 */
public class StillInCheckException extends InvalidMoveException {
  public StillInCheckException(String message) {
    super(message);
  }
}
