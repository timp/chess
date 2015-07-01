package toy.chess;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class MoveToSameSquareException extends InvalidMoveException {
  public MoveToSameSquareException(String message) {
    super(message);
  }
}
