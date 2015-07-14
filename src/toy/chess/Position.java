package toy.chess;

import java.util.List;

/**
 * A position on a Chess board.
 *
 * @author timp
 * @since 2015/06/29
 */
public class Position implements Cloneable {

  Board board = null;
  Square square = null;
  Piece piece = null;

  public Position(Board board, Square s) {
    this(board, s, null);
  }

  // Used by clone
  private Position() {}

  public Position(Board board, Square square, Piece piece) {
    this.board = board;
    this.square = square;
    this.piece = piece;
  }

  public Piece getPiece() {
    return piece;
  }
  public void setPiece(Piece piece) {
    this.piece = piece;
  }
  public String toString() {
    return square.toString() + " " + (piece == null ? " " :
        piece.getPlayer() + " " + piece.getName());
  }
  public String pic() {
    return piece == null ? " " : piece.getAbbreviation();
  }
  public Square getSquare() {
    return square;
  }
  public Board getBoard() {
    return board;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Position position = (Position) o;
 /*
    if (!board.equals(position.getBoard())) {
      return false;
    }
    */
    if (!square.equals(position.getSquare())) {
      return false;
    }
    return !(piece != null ? !piece.equals(position.piece)
        : position.piece != null);

  }

  @Override
  public int hashCode() {
    int result = board.getMoveNumber() + 1;
    result = 31 * result + square.hashCode();
    result = 31 * result + (piece != null ? piece.hashCode() : 0);
    return result;
  }

  @Override
  protected Object clone() {
    Position newPosition = new Position();
    if (square != null)
      newPosition.square = (Square) square.clone();
    if (piece != null)
      newPosition.piece = (Piece) piece.clone();
    return newPosition;
  }

  public List<Position> getPossibleMoves() {
    return getPiece().getPossibleMoves(board, getSquare());
  }
}
