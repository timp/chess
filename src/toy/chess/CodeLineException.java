package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class CodeLineException extends RuntimeException {
  public CodeLineException(String message, Exception cause) {
    super(message, cause);
  }
}
