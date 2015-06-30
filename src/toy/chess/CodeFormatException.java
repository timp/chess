package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class CodeFormatException extends RuntimeException {
  public CodeFormatException(String message) {
    super(message);
  }
  public CodeFormatException(String message, Exception cause) {
    super(message, cause);
  }
}
