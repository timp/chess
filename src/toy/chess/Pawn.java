package toy.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Pawn extends Piece {

  public Pawn(Player o) {
    super(o);
    name = "Pawn";
    abbreviation = "p";
  }

  @Override
  public void perform(Position from, Position to) {

    int distance = (to.getSquare().y() - from.getSquare().y())
        * from.getPiece().getPlayer().direction;
    if (distance == 1) {
      if (from.getSquare().file != to.getSquare().file) {
        if (Math.abs(from.getSquare().x() - to.getSquare().x()) == 1) {
          if (to.getPiece() == null) {
            Board b = to.getSquare().getBoard();
            if (b.getEnPassantCandidate() != null)
              if (b.getEnPassantCandidate().getSquare().y() ==
                  (to.getSquare().y() + b.getEnPassantCandidate().getPiece().getPlayer().getDirection())) {
                b.getEnPassantCandidate().setPiece(null);
                return;
              } else {
                throw new InvalidPieceMoveException(
                    "A " + getName() + " must move directly forward in the same file unless taking " +
                        from.getSquare().file + " != " + to.getSquare().file);
              }
          } else {
            return;
          }
        }
        throw new InvalidPieceMoveException(
              "A " + getName() + " must move directly forward in the same file unless taking " +
                  from.getSquare().file + " != " + to.getSquare().file);

      } else {
        if (to.getPiece() != null) {
          throw new InvalidPieceMoveException(
              "A " + getName() + " may not move on to an occupied square ");
        } else {
          return;
        }
      }
    } else if (distance == 2) {
      if ((from.getSquare().rank == Rank.TWO
          && from.getPiece().getPlayer() == Player.WHITE)
          ||
          (from.getSquare().rank == Rank.SEVEN
              && from.getPiece().getPlayer() == Player.BLACK)) {
        if (to.getPiece() != null) {
          throw new InvalidPieceMoveException(
              "A " + getName() + " may not move on to an occupied square ");
        } else {
          // Opponents next move may be en passant
          // We need to record that against the Board
          from.getSquare().getBoard().setEnPassantCandidate(to);
          return;
        }
      } else {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved");
      }
    }
    else {
        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move one square forward once moved (" + distance + ")");
    }

  }

  @Override
  public List<Position> getPossibleMoves(Square from) {
    ArrayList<Position> moves = new ArrayList<>();
    from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() + player.direction);
    if (from.x() * player.direction == 1
        || from.x() * player.direction == -6) {
      from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() + 2 * player.direction);
    }

    // The other possible moves are contingent on the square being
    // occupied by the opponent
    // TODO Should they be added here?

    return moves;
  }

  @Override
  public Object clone() {
    return new Pawn(player);
  }

}
