package toy.chess;

/**
 * Columns in a chess board..
 */
public enum File {
  A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7);

  private int coord;

  private static String[] names = "abcdefgh".split("");

  File(int yCoord) {
    this.coord = yCoord;
  }

  /** Exploiting the zero based index/coordinate coincidence. */
  public static File byIndex(int coord) {
    return values()[coord];
  }

  public static File byName(String name) {
    return valueOf(name.toUpperCase());
  }

  public static String[] names() {
    return names;
  }

  public int getCoord() {
    return coord;
  }

}
