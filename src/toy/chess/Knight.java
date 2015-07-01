package toy.chess;

import java.util.ArrayList;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Knight extends Piece {

  public Knight(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Knight";
  }

  /** See https://en.wikipedia.org/wiki/Descriptive_notation  */
  @Override
  public String getAbbreviation() {
    return getPlayer() == Player.BLACK ? "N" : "n";
  }

  @Override
  public void validate(Position from, Position to) {
    if (!getPossibleMoves(from.getSquare()).contains(to.getSquare())) {
      throw new InvalidPieceMoveException(
          " A " + getName() + " can only move two squares in one direction and one in another");

    }
  }
  private ArrayList<Square> getPossibleMoves(Square from) {
    ArrayList<Square> moves = new ArrayList<>(8);
    addIfLegal(moves, from.x()+1, from.y()+2);
    addIfLegal(moves, from.x()+2, from.y()+1);
    addIfLegal(moves, from.x()+2, from.y()-1);
    addIfLegal(moves, from.x()+1, from.y()-2);
    addIfLegal(moves, from.x() - 1, from.y() - 2);
    addIfLegal(moves, from.x()-2, from.y()-1);
    addIfLegal(moves, from.x()-2, from.y()+1);
    addIfLegal(moves, from.x()-1, from.y()+2);
    return moves;
  }

  private void addIfLegal(ArrayList<Square> moves, int x, int y) {
    try {
      moves.add(new Square(x, y));
    } catch (InvalidChessCoordinateException ignore) {}
  }

}
