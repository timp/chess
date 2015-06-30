package toy.chess;

/**
 * A position on a Chess board.
 *
 * A Chess board is, by convention, an 8 X 8 square, which we take for granted.
 *
 * @author timp
 * @since 2015/06/29
 */
public class Position {

    int x;
    int y;
    Rank rank;
    File file;
    Piece piece = null;

    public Position(int x, int y) {
        this(x, y, null);
    }

    public Position(int x, int y, Piece piece){
        if (valid(x))
            this.x = x;
        if (valid(y))
            this.y = y;
        this.piece = piece;
    }

    private boolean valid(int coord) {
        if (coord < 0 || coord > 7)
            throw new InvalidChessCoordinate(coord);
        return true;
    }

  public String toString() {
    return "" + x + " " + y + (piece == null ? " " : piece.getName());
  }

  public String toSquare() {
    return piece == null ? " " : piece.getAbbreviation();
  }


  public Piece getPiece() {
    return piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }
}
