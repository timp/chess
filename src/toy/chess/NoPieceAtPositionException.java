package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class NoPieceAtPositionException extends InvalidMoveException {
  public NoPieceAtPositionException(String message) {
    super(message);
  }
}
