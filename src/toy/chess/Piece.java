package toy.chess;

import java.util.List;

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

  public abstract void assertIsPossible(Position from, Position to);

  public abstract List<Position> getPossibleMoves(Board board, Square from);

  /** Deliberately does not declare CloneNotSupportedException
   * as we do not want the noise.
   */
  protected abstract Object clone();

}


