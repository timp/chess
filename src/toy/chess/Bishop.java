package toy.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Bishop extends Piece {

  public Bishop(Player o) {
    super(o);
    name = "Bishop";
    abbreviation = "b";
  }

  @Override
  public void assertIsPossible(Position from, Position to) {
    if (! from.getPossibleMoves().contains(to)) {
      throw new InvalidPieceMoveException("A " + getName() + " must move diagonally");
    }
  }

  @Override
  public List<Position> getPossibleMoves(Board board, Square from) {
    ArrayList<Position> moves = new ArrayList<>();
    for (int i = 1; i < 8; i++) {
      board.addIfStillOnBoard(moves, from.x() + i, from.y() + i);
      board.addIfStillOnBoard(moves, from.x() + i, from.y() - i);
      board.addIfStillOnBoard(moves, from.x() - i, from.y() - i);
      board.addIfStillOnBoard(moves, from.x() - i, from.y() + i);
    }
    return moves;
  }

  @Override
  public Object clone() {
    return new Bishop(player);
  }

}
