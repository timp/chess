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

  public Square(String squareCode) {
    this(new SquareCode(squareCode));
  }

  public Square(SquareCode squareCode) {
    this.rank = Rank.byName(squareCode.rank());
    this.file = File.byName(squareCode.file());
  }

  public String toString() {
    return  this.file.toString() + this.rank.toString();
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
