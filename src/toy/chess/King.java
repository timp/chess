package toy.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timp
 * @since 2015/06/29
 */
public class King extends Piece {

  public King(Player o) {
    super(o);
    name = "King";
    abbreviation = "k";
  }

  @Override
  public void perform(Position from, Position to) {
    if (
        (Math.abs(to.getSquare().x() - from.getSquare().x())
            == Math.abs(to.getSquare().y() - from.getSquare().y()))) {
      if (Math.abs(to.getSquare().y() - from.getSquare().y()) != 1) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " must move only one square diagonally");
      }
    } else {
      if (!((from.getSquare().x() == to.getSquare().x()
          && Math.abs(from.getSquare().y() - to.getSquare().y()) == 1)
          ||
          ((from.getSquare().y() == to.getSquare().y())
          && Math.abs(from.getSquare().x() - to.getSquare().x()) == 1)) ) {


        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move in one square in straight lines:"
        + Math.abs(from.getSquare().y() - to.getSquare().y()));
      }
    }
  }

  @Override
  public List<Position> getPossibleMoves(Square from) {
    ArrayList<Position> moves = new ArrayList<>(8);
    from.getBoard().addIfStillOnBoard(moves, from.x()+1, from.y());
    from.getBoard().addIfStillOnBoard(moves, from.x()+1, from.y()+1);
    from.getBoard().addIfStillOnBoard(moves, from.x(), from.y()+1);
    from.getBoard().addIfStillOnBoard(moves, from.x()-1, from.y()+1);
    from.getBoard().addIfStillOnBoard(moves, from.x()-1, from.y());
    from.getBoard().addIfStillOnBoard(moves, from.x()-1, from.y()-1);
    from.getBoard().addIfStillOnBoard(moves, from.x(), from.y()-1);
    from.getBoard().addIfStillOnBoard(moves, from.x()+1, from.y()-1);

    return moves;
  }

  @Override
  public Object clone() {
    return new King(player);
  }

}
