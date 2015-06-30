package toy.chess;

/**
 * Columns in a chess board.
 */
public enum File {
  ONE(0),
  TWO(1),
  THREE(2),
  FOUR(3),
  FIVE(4),
  SIX(5),
  SEVEN(6),
  EIGHT(7);

  int coord;

  File(int xCoord) {
    this.coord = xCoord;
  }

  /** Exploiting the zero based index/coordinate coincidence. */
  public static File byIndex(int coord) {
    return values()[coord];
  }

  public String toString() {
    return String.valueOf(this.coord + 1);
  }

  public int getCoord() {
    return coord;
  }
}
