package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class PositionOccupiedBySelfException extends InvalidMoveException {
  public PositionOccupiedBySelfException(String message) {
    super(message);
  }
}
