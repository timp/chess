package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Rook extends Piece {

  public Rook(Player o) {
    super(o);
    name = "Rook";
    abbreviation = "r";
  }

  @Override
  public void perform(Position from, Position to) {
    if (!(from.getSquare().x() == to.getSquare().x() ||
        from.getSquare().y() == to.getSquare().y()))
      throw new InvalidPieceMoveException(
          "A " + getName() +  " may only move in straight lines");
  }

  @Override
  public Object clone() {
    return new Rook(player);
  }
}
