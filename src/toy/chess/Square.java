package toy.chess;

/**
 * A single square within a chess board.
 *
 * @author timp
 * @since 2015-06-30.
 */
public class Square implements Cloneable {

  Rank rank;
  File file;

  // Used by clone
  private Square() {}

  public Square(int x, int y) {
    assertValid(x);
    assertValid(y);
    this.file = File.byIndex(x);
    this.rank = Rank.byIndex(y);
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

  @Override
  protected Object clone() {
    Square newSquare = new Square();
    newSquare.rank = rank;
    newSquare.file = file;
    return newSquare;
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
