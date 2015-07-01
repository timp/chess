package toy.chess;

import static toy.chess.Player.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Pawn extends Piece {

  public Pawn(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Pawn";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "P" : "p";
  }

  @Override
  public void validate(Position from, Position to) {
    int distance = (to.getSquare().y() - from.getSquare().y())
        * from.getPiece().getOwner().direction;
    if (distance == 2) {
      if (!((from.getSquare().rank == Rank.TWO
          && from.getPiece().getOwner() == Player.WHITE)
          ||
          (from.getSquare().rank == Rank.SEVEN
              && from.getPiece().getOwner() == Player.BLACK))) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved");
      }
    }
    else if (distance != 1) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved");
    }

    if (from.getSquare().file != to.getSquare().file) {
      if (to.getPiece() == null) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " must move directly forward unless taking " +
                from.getSquare().file + " != " +to.getSquare().file);
      }
    }
//TODO en passant
  }


}
