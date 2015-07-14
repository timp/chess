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
  public void assertIsPossible(Position from, Position to) {
    if (! from.getPossibleMoves().contains(to)) {
      if (! (to.getBoard().getCastleSquare() != null
          && (to.getBoard().getCastleSquare().toString().equals(
          to.getSquare().toString())))) {
        throw new InvalidPieceMoveException("A " + getName() + " may only move one square in any direction");
      }
    }
  }

  @Override
  public List<Position> getPossibleMoves(Board board, Square from) {
    ArrayList<Position> moves = new ArrayList<>(8);
    board.addIfStillOnBoard(moves, from.x()+1, from.y());
    board.addIfStillOnBoard(moves, from.x()+1, from.y()+1);
    board.addIfStillOnBoard(moves, from.x(), from.y()+1);
    board.addIfStillOnBoard(moves, from.x()-1, from.y()+1);
    board.addIfStillOnBoard(moves, from.x()-1, from.y());
    board.addIfStillOnBoard(moves, from.x()-1, from.y()-1);
    board.addIfStillOnBoard(moves, from.x(), from.y()-1);
    board.addIfStillOnBoard(moves, from.x()+1, from.y()-1);

    return moves;
  }

  @Override
  public Object clone() {
    return new King(player);
  }

}
