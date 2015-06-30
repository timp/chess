package toy.chess;

/**
 * Columns in a chess board.
 */
public enum File {
  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8");

  String numeric;
  File(String numeric) {
    this.numeric = numeric;
  }

  public String toString() {
    return this.numeric;
  }
}
