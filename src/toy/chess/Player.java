package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public enum Player {

  WHITE(1), BLACK(-1);
  public int direction;
  Player(int direction) {
    this.direction = direction;
  }

  /**
   * White pawns move in positive increments on the x axis,
   * black pawns in decrements.
   */
  public int getDirection() {
    return direction;
  }

  public Player getOpponent() {
    if (direction == 1) {
      return BLACK;
    } else {
      return WHITE;
    }
  }
  @Override
  public String toString() {
    if (direction == 1) {
      return "White";
    } else {
      return "Black";
    }
  }
}
