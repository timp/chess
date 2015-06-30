package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Board {

  private Position[][] positions = new Position[8][8];

  public Board() {
    // First square (a1 or 0,0) must be black and occupied by White
    positions[0][0] = new Position(new Square("a1"), new Rook(Player.WHITE));
    // white
    positions[1][0] = new Position(new Square("a2"), new Knight(Player.WHITE));
    // black
    positions[2][0] = new Position(new Square("a3"), new Bishop(Player.WHITE));
    // Queen on her own colour
    // white
    positions[3][0] = new Position(new Square("a4"), new Queen(Player.WHITE));
    positions[4][0] = new Position(new Square("a5"), new King(Player.WHITE));
    positions[5][0] = new Position(new Square("a6"), new Bishop(Player.WHITE));
    positions[6][0] = new Position(new Square("a7"), new Knight(Player.WHITE));
    positions[7][0] = new Position(new Square("a8"), new Rook(Player.WHITE));

    for (int x = 0; x < 8; x++) {
      positions[x][1] = new Position(
          new Square("b" + File.names()[x]), new Pawn(Player.WHITE));
    }


    for (int y = 2; y < 6; y++) {
      for (int x = 0; x < 8; x++) {
        positions[x][y] = new Position(
            new Square(Rank.names()[x] + File.names()[y]));
      }
    }

    for (int x = 0; x < 8; x++) {
      positions[x][6] = new Position(
          new Square("g" + File.names()[x]), new Pawn(Player.WHITE));
    }

    positions[0][7] = new Position(new Square("h1"), new Rook(Player.WHITE));
    positions[1][7] = new Position(new Square("h2"), new Knight(Player.WHITE));
    positions[2][7] = new Position(new Square("h3"), new Bishop(Player.WHITE));
    // King on the opposite of his queen's colour
    positions[3][7] = new Position(new Square("h4"), new King(Player.WHITE));
    // queen on her own colour
    positions[4][7] = new Position(new Square("h5"), new Queen(Player.WHITE));
    positions[5][7] = new Position(new Square("h6"), new Bishop(Player.WHITE));
    positions[6][7] = new Position(new Square("h7"), new Knight(Player.WHITE));
    positions[7][7] = new Position(new Square("h8"), new Rook(Player.WHITE));
  }

  /** Note that a1 is in the top left, not bottom left */
  public String toString() {
    StringBuilder it = new StringBuilder();
    it.append('+');
    it.append("--------");
    it.append('+');
    it.append("\n");
    for (int y = 0; y < 8; y++) {
      it.append('|');
      for (int x = 0; x < 8; x++) {
        it.append(positions[x][y].toSquare());
      }
      it.append('|');
      it.append("\n");
    }
    it.append('+');
    it.append("--------");
    it.append('+');
    it.append("\n");
    return it.toString();
  }

  /** Note that a1 is in the top left, not bottom left */
  public String pic() {
    String pic = "";
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        pic += positions[x][y].getSquare().pic();
      }
      pic += "\n";
    }
    return pic;
  }

  public Board move(int[] fromCoords, int[] toCoords) {
    Position from = positions[fromCoords[0]][fromCoords[1]];
    Position to = positions[toCoords[0]][toCoords[1]];
    Path path = validate(from, to);

    // TODO Validate path

    to.setPiece(from.getPiece());
    from.setPiece(null);
    return this;
  }

  public Board move(MoveCode fromTo) {
    Square fromSquare = new Square(fromTo.from());
    Square toSquare = new Square(fromTo.to());

    Position from = positions[fromSquare.x()][fromSquare.y()];
    Position to = positions[toSquare.x()][toSquare.y()];

    to.setPiece(from.getPiece());
    from.setPiece(null);

    return this;
  }
  public Path validate(Position current, Position candidate) {
    if (current.getPiece() == null)
      throw new InvalidMoveException("No piece to move at " + current);

    if (candidate.getPiece() != null && (candidate.getPiece().getOwner() == current.getPiece().getOwner())) {
      throw new InvalidMoveException("Player already occupies " + candidate);
    }
    // TODO return path

    return new Path();
  }


}
