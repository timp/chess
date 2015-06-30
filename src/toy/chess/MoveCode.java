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
  public SquareCode fromRank() {
    return new SquareCode(code.substring(0,1));
  }
  public SquareCode fromFile() {
    return new SquareCode(code.substring(1,0));
  }
  public SquareCode toRank() {
    return new SquareCode(code.substring(2,3));
  }
  public SquareCode toFile() {
    return new SquareCode(code.substring(3,4));
  }
}
