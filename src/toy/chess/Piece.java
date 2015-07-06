package toy.chess;

import static toy.chess.Player.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public abstract class Piece implements Cloneable {
  Player player = null;

  String name;
  String abbreviation;


  public Piece(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }

  public String getName() {
    return name;
  }

  /**
   * Black pieces are uppercase, white lowercase
   */
  public String getAbbreviation() {
    return getPlayer() == BLACK ?
        abbreviation.toUpperCase() : abbreviation.toLowerCase();

  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    Piece newPiece = (Piece) super.clone();
    newPiece.player = player;
    return newPiece;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Piece piece = (Piece) o;

    if (player != piece.player) return false;
    if (!name.equals(piece.name)) return false;
    return abbreviation.equals(piece.abbreviation);

  }

  @Override
  public int hashCode() {
    int result = player.ordinal();
    result = 31 * result + abbreviation.hashCode();
    return result;
  }

  public abstract void validate(Position from, Position to);


}


