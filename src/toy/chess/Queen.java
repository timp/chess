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
    if (! from.getPossibleMoves().contains(to)) {
      throw new InvalidPieceMoveException(
          "A " + getName() + " must move diagonally or in straight lines");
    }
  }

  /** Any unobstructed horizontal, vertical or diagonal move of any length.*/
  @Override
  public List<Position> getPossibleMoves(Board board, Square from) {
    ArrayList<Position> moves = new ArrayList<>(8);
    for (int i = 1; i < 8; i++) {
      board.addIfStillOnBoard(moves, from.x() + i, from.y() + i);
      board.addIfStillOnBoard(moves, from.x() + i, from.y() - i);
      board.addIfStillOnBoard(moves, from.x() - i, from.y() - i);
      board.addIfStillOnBoard(moves, from.x() - i, from.y() + i);

      board.addIfStillOnBoard(moves, from.x(), from.y() + i);
      board.addIfStillOnBoard(moves, from.x(), from.y() - i);
      board.addIfStillOnBoard(moves, from.x() + i, from.y());
      board.addIfStillOnBoard(moves, from.x() - i, from.y());
    }
    return moves;
  }

  @Override
  public Object clone() {
    return new Queen(player);
  }
}
