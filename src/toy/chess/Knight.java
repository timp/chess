package toy.chess;

import java.util.ArrayList;

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
  public void perform(Position from, Position to) {
    if (!getPossibleMoves(from.getSquare()).contains(to.getSquare())) {
      throw new InvalidPieceMoveException(
          " A " + getName() + " can only move two squares in one direction and one in another");

    }
  }
  private ArrayList<Square> getPossibleMoves(Square from) {
    ArrayList<Square> moves = new ArrayList<>(8);
    addIfLegal(moves, from.getBoard(), from.x()+1, from.y()+2);
    addIfLegal(moves, from.getBoard(), from.x()+2, from.y()+1);
    addIfLegal(moves, from.getBoard(), from.x()+2, from.y()-1);
    addIfLegal(moves, from.getBoard(), from.x()+1, from.y()-2);
    addIfLegal(moves, from.getBoard(), from.x() - 1, from.y() - 2);
    addIfLegal(moves, from.getBoard(), from.x()-2, from.y()-1);
    addIfLegal(moves, from.getBoard(), from.x()-2, from.y()+1);
    addIfLegal(moves, from.getBoard(), from.x()-1, from.y()+2);
    return moves;
  }

  private void addIfLegal(ArrayList<Square> moves, Board board, int x, int y) {
    try {
      moves.add(new Square(board, x, y));
    } catch (InvalidChessCoordinateException ignore) {}
  }

  @Override
  public Object clone() {
    return new Knight(player);
  }
}
