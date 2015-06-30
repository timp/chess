package toy.chess;

/**
 * A position on a Chess board.
 *
 * @author timp
 * @since 2015/06/29
 */
public class Position {


  Square square = null;
  Piece piece = null;

  public Position(Square s) {
    this(s, null);
  }

  public Position(Square square, Piece piece) {
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
    return square.toString() + (piece == null ? " " : piece.getName());
  }

  public String toSquare() {
    return piece == null ? " " : piece.getAbbreviation();
  }


  public Square getSquare() {
    return square;
  }
}
