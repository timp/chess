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
}
