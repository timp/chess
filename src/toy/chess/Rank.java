package toy.chess;

/**
 * Rows in a chess board..
 */
public enum Rank {
  A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

  private int coord;

  Rank(int yCoord) {
    this.coord = yCoord;
  }

  /** Exploiting the zero based index/coordinate coincidence. */
  public static Rank byIndex(int coord) {
    return values()[coord];
  }

  public int getCoord() {
    return coord;
  }

}
