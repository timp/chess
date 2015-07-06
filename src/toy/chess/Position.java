package toy.chess;

/**
 * A position on a Chess board.
 *
 * @author timp
 * @since 2015/06/29
 */
public class Position implements Cloneable {

  Square square = null;
  Piece piece = null;

  public Position(Square s) {
    this(s, null);
  }

  // Used by clone
  private Position() {}

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
    return square.toString() + " " + (piece == null ? " " : piece.getName());
  }
  public String pic() {
    return piece == null ? " " : piece.getAbbreviation();
  }
  public Square getSquare() {
    return square;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Position position = (Position) o;

    if (!square.equals(position.square)) {
      return false;
    }
    return !(piece != null ? !piece.equals(position.piece)
        : position.piece != null);

  }

  @Override
  public int hashCode() {
    int result = square.hashCode();
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

}
