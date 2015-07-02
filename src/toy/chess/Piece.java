package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public abstract class Piece {
  Player player = null;

  public Piece(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public abstract String getName();

  /**
   * Black pieces are uppercase, white lowercase
   */
  public abstract String getAbbreviation();

  public abstract void validate(Position from, Position to);

}


