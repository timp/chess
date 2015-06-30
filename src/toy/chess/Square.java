package toy.chess;

/**
 * A single square within a chess board.
 *
 * @author timp
 * @since 2015-06-30.
 */
public class Square {

  Rank rank;
  File file;

  public Square(int x, int y) {
    assertValid(x);
    assertValid(y);
    this.rank = Rank.byIndex(y);
    this.file = File.byIndex(x);
  }

  public Square(String squareCode) {
    this(new SquareCode(squareCode));
  }

  public Square(SquareCode squareCode) {
    this.rank = Rank.byName(squareCode.rank());
    this.file = File.byName(squareCode.file());
  }

  private boolean assertValid(int coord) {
    if (coord < 0 || coord > 7)
      throw new InvalidChessCoordinate(coord);
    return true;
  }

  public String toString() {
    return this.rank.toString() + this.file.toString();
  }

  public int x() {
    return file.getCoord();
  }
  public int y() {
    return rank.getCoord();
  }

  public String pic() {
    if((x() + y()) % 2 == 0){
      return "#";
    } else {
      return " ";
    }
  }
}
