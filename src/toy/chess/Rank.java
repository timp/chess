package toy.chess;

/**
 * Rows in a chess board.
 */
public enum Rank {
  ONE(0),
  TWO(1),
  THREE(2),
  FOUR(3),
  FIVE(4),
  SIX(5),
  SEVEN(6),
  EIGHT(7);

  int coord;
  private static String[] names = "12345678".split("");

  Rank(int xCoord) {
    this.coord = xCoord;
  }

  /** Exploiting the zero based index/coordinate coincidence. */
  public static Rank byIndex(int coord) {
    return values()[coord];
  }
  public static Rank byName(String name) {
    for (Rank v : values()) {
      if (String.valueOf(v.coord + 1).equals(name)) {
        return v;
      }
    }
    throw new IllegalArgumentException("No File named '" + name + "'");
  }

  public static String[] names() {
    return names;
  }

  public String toString() {
    return String.valueOf(this.coord + 1);
  }

  public int getCoord() {
    return coord;
  }
}
