package toy.chess;

import java.util.ArrayList;
import java.util.List;

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
  public void assertIsPossible(Position from, Position to) {
    if (! from.getPossibleMoves().contains(to)) {
      throw new InvalidPieceMoveException(
          "A " + getName() + " may only move in straight lines");
    }
  }

  @Override
  public List<Position> getPossibleMoves(Board board, Square from) {
    ArrayList<Position> moves = new ArrayList<>(16);
    for (int i = 1; i < 8; i++) {
      board.addIfStillOnBoard(moves, from.x(), from.y() + i);
      board.addIfStillOnBoard(moves, from.x(), from.y() - i);
      board.addIfStillOnBoard(moves, from.x() + i, from.y());
      board.addIfStillOnBoard(moves, from.x() - i, from.y());
    }
    return moves;
  }

  @Override
  public Object clone() {
    return new Rook(player);
  }
}
