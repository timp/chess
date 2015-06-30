package toy.chess;

/**
 * A single square within a chess board.
 * <p>
 * A Chess board is, by convention, an 8 X 8 square,
 * we ensure these dimensions here.
 *
 * @author timp
 * @since 2015-06-30.
 */
public class Square {

  int x;
  int y;
  Rank rank;
  File file;

  public Square(int x, int y) {
    assertValid(x);
    assertValid(y);
    this.rank = Rank.byIndex(y);
    this.file = File.byIndex(x);
  }

  private boolean assertValid(int coord) {
    if (coord < 0 || coord > 7)
      throw new InvalidChessCoordinate(coord);
    return true;
  }

}
