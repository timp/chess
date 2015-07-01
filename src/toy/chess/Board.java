package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Board {

  private Position[][] positions = new Position[8][8];


  private Player player1;
  private Player player2;

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public Board() {
    // First square (a1 or 0,0) must be black and occupied by White
    positions[0][0] = new Position(new Square("a1"), new Rook(Player.WHITE));
    // white
    positions[1][0] = new Position(new Square("b1"), new Knight(Player.WHITE));
    // black
    positions[2][0] = new Position(new Square("c1"), new Bishop(Player.WHITE));
    // Queen on her own colour
    // white
    positions[3][0] = new Position(new Square("d1"), new Queen(Player.WHITE));
    positions[4][0] = new Position(new Square("e1"), new King(Player.WHITE));
    positions[5][0] = new Position(new Square("f1"), new Bishop(Player.WHITE));
    positions[6][0] = new Position(new Square("g1"), new Knight(Player.WHITE));
    positions[7][0] = new Position(new Square("h1"), new Rook(Player.WHITE));

    for (int x = 0; x < 8; x++) {
      positions[x][1] = new Position(
          new Square(File.names()[x] + "2" ), new Pawn(Player.WHITE));
    }


    for (int y = 2; y < 6; y++) {
      for (int x = 0; x < 8; x++) {
        positions[x][y] = new Position(
            new Square(File.names()[x] + Rank.names()[y] ));
      }
    }

    for (int x = 0; x < 8; x++) {
      positions[x][6] = new Position(
          new Square(File.names()[x] + "7"), new Pawn(Player.BLACK));
    }

    // white
    positions[0][7] = new Position(new Square("a8"), new Rook(Player.BLACK));
    // black
    positions[1][7] = new Position(new Square("b8"), new Knight(Player.BLACK));
    // white
    positions[2][7] = new Position(new Square("c8"), new Bishop(Player.BLACK));
    // black
    // queen on her own colour
    positions[3][7] = new Position(new Square("d8"), new Queen(Player.BLACK));
    // King on the opposite of his queen's colour
    // white
    positions[4][7] = new Position(new Square("e8"), new King(Player.BLACK));
    positions[5][7] = new Position(new Square("f8"), new Bishop(Player.BLACK));
    positions[6][7] = new Position(new Square("g8"), new Knight(Player.BLACK));
    positions[7][7] = new Position(new Square("h8"), new Rook(Player.BLACK));
  }

  /** Note that we need to flip the matrix to get normal chess notation. */
  public String toString() {
    StringBuilder it = new StringBuilder();
    it.append("  abcdefgh  \n");
    it.append(" +--------+ \n");
    for (int y = 7; y > -1; y--) {
      it.append(y+1);
      it.append('|');
      for (int x = 0; x < 8; x++) {
        it.append(positions[x][y].pic());
      }
      it.append('|');
      it.append(y+1);
      it.append("\n");
    }
    it.append(" +--------+ \n");
    it.append("  abcdefgh  \n");
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

  public String names() {
    String pic = "";
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        pic += positions[x][y].getSquare().toString();
      }
      pic += "\n";
    }
    return pic;
  }

  public Board m(String moveCode) {
    return move(new MoveCode(moveCode));
  }

  public Board move(MoveCode fromTo) {
    Square fromSquare = new Square(fromTo.from());
    Square toSquare = new Square(fromTo.to());

    Position from = positions[fromSquare.x()][fromSquare.y()];
    Position to = positions[toSquare.x()][toSquare.y()];
    if (player1 == null) {
      player1 = from.getPiece().getPlayer();
    } else {
      if (player2 == null) {
        player2 = from.getPiece().getPlayer();
      }
    }

    // TODO Validate path
    validate(from, to);
    // TODO Queening

    to.setPiece(from.getPiece());
    from.setPiece(null);
    return this;
  }
  public Path validate(Position current, Position candidate) {
    if (current.getPiece() == null)
      throw new NoPieceAtPositionException("No piece to move at " + current);

    if (candidate.getPiece() != null && (candidate.getPiece().getPlayer() == current.getPiece().getPlayer())) {
      throw new PositionOccupiedBySelfException(
          "Player already occupies " + candidate);
    }


    current.getPiece().validate(current, candidate);

    // TODO return path
    return new Path();
  }


  public Position getPosition(String squareName) {
    Square s = new Square(squareName);
    return positions[s.x()][s.y()];
  }
}
