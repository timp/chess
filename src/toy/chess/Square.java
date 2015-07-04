package toy.chess;

/**
 * A single square within a chess board.
 *
 * @author timp
 * @since 2015-06-30.
 */
public class Square {

  Board board;
  Rank rank;
  File file;

  public Square(Board board, int x, int y) {
    this.board = board;
    assertValid(x);
    assertValid(y);
    this.file = File.byIndex(x);
    this.rank = Rank.byIndex(y);
  }

  public Square(Board board, String squareCode) {
    this(board, new SquareCode(squareCode));
  }

  public Square(Board board, SquareCode squareCode) {
    this.board = board;
    this.rank = Rank.byName(squareCode.rank());
    this.file = File.byName(squareCode.file());
  }

  public Board getBoard() {
    return board;
  }

  private boolean assertValid(int coord) {
    if (coord < 0 || coord > 7)
      throw new InvalidChessCoordinateException(coord);
    return true;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Square square = (Square) o;

    if (rank != square.rank) return false;
    return file == square.file;

  }

  @Override
  public int hashCode() {
    int result = rank.ordinal();
    result = 31 * result + file.ordinal();
    return result;
  }

}
