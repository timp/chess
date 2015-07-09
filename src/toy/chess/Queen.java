package toy.chess;

import java.util.ArrayList;
import java.util.List;


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

  @Override
  public void assertIsPossible(Position from, Position to) {
    if (! getPossibleMoves(from.getSquare()).contains(to)) {
      throw new InvalidPieceMoveException(
          "A " + getName() + " must move diagonally or in straight lines");
    }
  }

  /** Any unobstructed horizontal, vertical or diagonal move of any length.*/
  @Override
  public List<Position> getPossibleMoves(Square from) {
    ArrayList<Position> moves = new ArrayList<>(8);
    for (int i = 1; i < 8; i++) {
      from.getBoard().addIfStillOnBoard(moves, from.x() + i, from.y() + i);
      from.getBoard().addIfStillOnBoard(moves, from.x() + i, from.y() - i);
      from.getBoard().addIfStillOnBoard(moves, from.x() - i, from.y() - i);
      from.getBoard().addIfStillOnBoard(moves, from.x() - i, from.y() + i);

      from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() + i);
      from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() - i);
      from.getBoard().addIfStillOnBoard(moves, from.x() + i, from.y());
      from.getBoard().addIfStillOnBoard(moves, from.x() - i, from.y());
    }
    return moves;
  }

  @Override
  public Object clone() {
    return new Queen(player);
  }
}
