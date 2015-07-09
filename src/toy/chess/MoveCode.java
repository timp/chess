package toy.chess;

/**                            `
 * @author timp
 * @since 2015-06-30.
 */
public class MoveCode {

  String code;
  public MoveCode(String code) {
    if (code.length() != 4) {
      throw new CodeFormatException(
          "Code '" + code + "' has wrong format");
    }
    this.code = code;
  }
  public SquareCode from() {
    return new SquareCode(code.substring(0,2));
  }
  public SquareCode to() {
    return new SquareCode(code.substring(2));
  }
  @Override
  public String toString(){
    return code;
  }
}
