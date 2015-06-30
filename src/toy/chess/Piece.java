package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public abstract class Piece {
  Player owner = null;

  public Piece(Player owner) {
    this.owner = owner;
  }

  public Player getOwner() {
    return this.owner;
  }

  public abstract String getName();

  /**
   * Black pieces are uppercase, white lowercase
   */
  public abstract String getAbbreviation();

  public abstract void validate(Position from, Position to);

}

