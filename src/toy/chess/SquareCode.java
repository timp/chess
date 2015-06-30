package toy.chess;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class SquareCode {
  String code;
  public SquareCode(String code) {
    if (code.length() != 2) {
      throw new CodeFormatException(
          "Code '" + code + "' is not 2 characters long");
    }
    this.code = code;
  }
  public String rank() {
    return code.substring(0,1);
  }
  public String file() {
    return code.substring(1);
  }

}
