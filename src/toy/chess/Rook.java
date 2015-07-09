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
  public void perform(Position from, Position to) {
    if (!(from.getSquare().x() == to.getSquare().x() ||
        from.getSquare().y() == to.getSquare().y()))
      throw new InvalidPieceMoveException(
          "A " + getName() +  " may only move in straight lines");
  }

  @Override
  public List<Position> getPossibleMoves(Square from) {
    ArrayList<Position> moves = new ArrayList<>(16);
    for (int i = 1; i < 8; i++) {
      from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() + i);
      from.getBoard().addIfStillOnBoard(moves, from.x(), from.y() - i);
      from.getBoard().addIfStillOnBoard(moves, from.x() + i, from.y());
      from.getBoard().addIfStillOnBoard(moves, from.x() - i, from.y());
    }
    return moves;
  }

  @Override
  public Object clone() {
    return new Rook(player);
  }
}
