package toy.chess;

import static toy.chess.Player.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Queen extends Piece {

  public Queen(Player o) {
    super(o);
    name = "Queen";
    abbreviation = "q";
  }

  /** Any unobstructed horizontal, vertical or diagonal move of any length.*/
  @Override
  public void perform(Position from, Position to) {
    if ((
        Math.abs(to.getSquare().x() - from.getSquare().x())
            == Math.abs(to.getSquare().y() - from.getSquare().y()))
        ) {
      return;
    }
    if ((from.getSquare().x() == to.getSquare().x() ||
        from.getSquare().y() == to.getSquare().y())) {
      return;
    }
    throw new InvalidPieceMoveException(
        "A " + getName() + " must move diagonally or in straight lines");

  }


  @Override
  public Object clone() {
    return new Queen(player);
  }
}
