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
    return getPlayer() == BLACK ? "P" : "p";
  }

  @Override
  public void validate(Position from, Position to) {

    int distance = (to.getSquare().y() - from.getSquare().y())
        * from.getPiece().getPlayer().direction;
    if (distance == 2) {
      if ((from.getSquare().rank == Rank.TWO
          && from.getPiece().getPlayer() == Player.WHITE)
          ||
          (from.getSquare().rank == Rank.SEVEN
              && from.getPiece().getPlayer() == Player.BLACK)) {
        // Opponents next move may be en passant
        from.getSquare().getBoard().setEnPassantCandidate(to);
        // We need to record that against the Board
      } else {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved");
      }
    }
    else if (distance != 1) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved (" + distance + ")");
    }

    if (from.getSquare().file != to.getSquare().file) {
      if (Math.abs(from.getSquare().x() - to.getSquare().x()) == 1)  {
        if (to.getPiece() == null) {
          Board b = to.getSquare().getBoard();
          if (b.getEnPassantCandidate() != null
              && b.getEnPassantCandidate().getSquare().y() ==
              (to.getSquare().y() + b.getEnPassantCandidate().getPiece().getPlayer().getDirection()) ) {
                  b.getEnPassantCandidate().setPiece(null);
          } else {
            throw new InvalidPieceMoveException(
                "A " + getName() + " must move directly forward in the same file unless taking " +
                    from.getSquare().file + " != " + to.getSquare().file);
          }
        }
      } else {
        throw new InvalidPieceMoveException(
            "A " + getName() + " must move directly forward in the same file unless taking " +
                from.getSquare().file + " != " + to.getSquare().file);
      }
    }
  }


}
