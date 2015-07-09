package toy.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Knight extends Piece {

  public Knight(Player o) {
    super(o);
    name = "Knight";
    abbreviation = "n";
  }

  @Override
  public void assertIsPossible(Position from, Position to) {
    if (!getPossibleMoves(from.getSquare()).contains(to)) {
      throw new InvalidPieceMoveException(
          " A " + getName() + " can only move two squares in one direction and one in another");
    }
  }

  @Override
  public List<Position> getPossibleMoves(Square from) {
    ArrayList<Position> moves = new ArrayList<>(8);
    from.getBoard().addIfStillOnBoard(moves, from.x() + 1, from.y() + 2);
    from.getBoard().addIfStillOnBoard(moves, from.x() + 2, from.y() + 1);
    from.getBoard().addIfStillOnBoard(moves, from.x() + 2, from.y() - 1);
    from.getBoard().addIfStillOnBoard(moves, from.x() + 1, from.y() - 2);
    from.getBoard().addIfStillOnBoard(moves, from.x() - 1, from.y() - 2);
    from.getBoard().addIfStillOnBoard(moves, from.x() - 2, from.y() - 1);
    from.getBoard().addIfStillOnBoard(moves, from.x() - 2, from.y() + 1);
    from.getBoard().addIfStillOnBoard(moves, from.x() - 1, from.y() + 2);
    return moves;
  }

  @Override
  public Object clone() {
    return new Knight(player);
  }
}
