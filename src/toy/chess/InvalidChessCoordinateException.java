package toy.chess;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class InvalidChessCoordinateException extends RuntimeException {
  public InvalidChessCoordinateException(int coord) {
    super("Invalid coordinate: " + coord);
  }
}
