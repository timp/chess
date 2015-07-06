package toy.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Board {

  private Position[][] positions = new Position[8][8];

  private Player player1;
  private Player player2;

  private Position enPassantCandidate;

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public Board() {
    // First square (a1 or 0,0) must be black and occupied by White
    positions[0][0] = new Position(new Square(this, "a1"), new Rook(Player.WHITE));
    // white
    positions[1][0] = new Position(new Square(this, "b1"), new Knight(Player.WHITE));
    // black
    positions[2][0] = new Position(new Square(this, "c1"), new Bishop(Player.WHITE));
    // Queen on her own colour
    // white
    positions[3][0] = new Position(new Square(this, "d1"), new Queen(Player.WHITE));
    positions[4][0] = new Position(new Square(this, "e1"), new King(Player.WHITE));
    positions[5][0] = new Position(new Square(this, "f1"), new Bishop(Player.WHITE));
    positions[6][0] = new Position(new Square(this, "g1"), new Knight(Player.WHITE));
    positions[7][0] = new Position(new Square(this, "h1"), new Rook(Player.WHITE));

    for (int x = 0; x < 8; x++) {
      positions[x][1] = new Position(
          new Square(this, File.names()[x] + "2"), new Pawn(Player.WHITE));
    }


    for (int y = 2; y < 6; y++) {
      for (int x = 0; x < 8; x++) {
        positions[x][y] = new Position(
            new Square(this, File.names()[x] + Rank.names()[y]));
      }
    }

    for (int x = 0; x < 8; x++) {
      positions[x][6] = new Position(
          new Square(this, File.names()[x] + "7"), new Pawn(Player.BLACK));
    }

    // white
    positions[0][7] = new Position(new Square(this, "a8"), new Rook(Player.BLACK));
    // black
    positions[1][7] = new Position(new Square(this, "b8"), new Knight(Player.BLACK));
    // white
    positions[2][7] = new Position(new Square(this, "c8"), new Bishop(Player.BLACK));
    // black
    // queen on her own colour
    positions[3][7] = new Position(new Square(this, "d8"), new Queen(Player.BLACK));
    // King on the opposite of his queen's colour
    // white
    positions[4][7] = new Position(new Square(this, "e8"), new King(Player.BLACK));
    positions[5][7] = new Position(new Square(this, "f8"), new Bishop(Player.BLACK));
    positions[6][7] = new Position(new Square(this, "g8"), new Knight(Player.BLACK));
    positions[7][7] = new Position(new Square(this, "h8"), new Rook(Player.BLACK));
  }

  /**
   * Note that we need to flip the matrix to get normal chess notation.
   */
  public String toString() {
    StringBuilder it = new StringBuilder();
    it.append("  abcdefgh  \n");
    it.append(" +--------+ \n");
    for (int y = 7; y > -1; y--) {
      it.append(y + 1);
      it.append('|');
      for (int x = 0; x < 8; x++) {
        it.append(positions[x][y].pic());
      }
      it.append('|');
      it.append(y + 1);
      it.append("\n");
    }
    it.append(" +--------+ \n");
    it.append("  abcdefgh  \n");
    return it.toString();
  }

  /**
   * Note that a1 is in the top left, not bottom left
   */
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
    Square fromSquare = new Square(this, fromTo.from());
    Square toSquare = new Square(this, fromTo.to());

    Position from = positions[fromSquare.x()][fromSquare.y()];
    Position to = positions[toSquare.x()][toSquare.y()];
    if (player1 == null) {
      player1 = from.getPiece().getPlayer();
    } else {
      if (player2 == null) {
        player2 = from.getPiece().getPlayer();
      }
    }


    if (getEnPassantCandidate() != null) {
      if (getEnPassantCandidate().getPiece() == null) {
        setEnPassantCandidate(null);
      } else {
        if (getEnPassantCandidate().getPiece().getPlayer()
            == from.getPiece().getPlayer()) {

          setEnPassantCandidate(null);
        }
      }
    }


    // TODO Validate path
    validate(from, to);

    to.setPiece(from.getPiece());
    from.setPiece(null);
    if (to.getPiece() instanceof Pawn) {
      if (to.getSquare().y() == 0 || to.getSquare().y() == 7) {
        to.setPiece(new Queen(to.getPiece().getPlayer()));
      }
    }
    return this;
  }

  public List<Square> getPath(Square from, Square to) {
    ArrayList<Square> path = new ArrayList();
    if (from.x() == to.x()) {
      if (from.y() > to.y()) {
        for (int y = from.y() - 1; y > to.y(); y--) {
          if (positions[from.x()][y].getPiece() != null) {
            path.add(positions[from.x()][y].getSquare());
          }
        }
      } else {
        for (int y = from.y() + 1; y < to.y(); y++) {
          if (positions[from.x()][y].getPiece() != null) {
            path.add(positions[from.x()][y].getSquare());
          }
        }
      }
    } else {
      if (from.y() == to.y()) {
        if (from.x() > to.x()) {
          for (int x = from.x() - 1; x > to.x(); x--) {
            if (positions[x][from.y()].getPiece() != null) {
              path.add(positions[x][from.y()].getSquare());
            }
          }
        } else {
          for (int x = from.x() + 1; x < to.x(); x++) {
            if (positions[x][from.y()].getPiece() != null) {
              path.add(positions[x][from.y()].getSquare());
            }
          }
        }
      } else if ((from.x() < to.x()) && (from.y() < to.y())) {
        // NE
        for (int x = from.x() + 1, y = from.y() + 1;
              x < to.x(); x++, y++) {
            if (positions[x][y].getPiece() != null) {
              path.add(positions[x][y].getSquare());
            }
        }
      } else if ((from.x() < to.x()) && (from.y() > to.y())  ){
        // SE
        for (int x = from.x() + 1, y = from.y() - 1;
             x < to.x(); x++, y--) {
          if (positions[x][y].getPiece() != null) {
            path.add(positions[x][y].getSquare());
          }
        }
      } else if ((from.x() > to.x()) && (from.y() > to.y())){
        System.err.println("SW");
        for (int x = from.x() - 1, y = from.y() - 1;
             x > to.x(); x--, y--) {
          if (positions[x][y].getPiece() != null) {
            path.add(positions[x][y].getSquare());
          }
        }
      } else if ((from.x() > to.x()) && (from.y() < to.y())){
        // NW
        for (int x = from.x() - 1, y = from.y() + 1;
             x > to.x(); x--, y++) {
          if (positions[x][y].getPiece() != null) {
            path.add(positions[x][y].getSquare());
          }
        }
      }
    }
    return path;
  }

  public void validate(Position current, Position candidate) {
    boolean castling = false;
    if (current.getPiece() == null) {
      throw new NoPieceAtPositionException("No piece to move at " + current);
    } else {
      if (candidate.getPiece() != null
          && candidate.getPiece().getPlayer() == current.getPiece().getPlayer()) {
        if (current.getPiece() instanceof Rook &&
            candidate.getPiece() instanceof King) {
          if (current.getSquare().x() > candidate.getSquare().x()) {
            positions[candidate.getSquare().x() + 1][candidate.getSquare().y()]
                .setPiece(candidate.getPiece());
          } else {
            positions[candidate.getSquare().x() - 1][candidate.getSquare().y()]
                .setPiece(candidate.getPiece());
          }
          castling = true;
        } else {
          throw new PositionOccupiedBySelfException(
              "Player already occupies " + candidate);
        }
      }
    }

    current.getPiece().validate(current, candidate);

    if (!(current.getPiece() instanceof Knight
        || current.getPiece() instanceof King)) {
      List<Square> path = getPath(current.getSquare(), candidate.getSquare());
      if (!path.isEmpty()) {
        if (!(castling && path.size() == 1)) {
          throw new InvalidPieceMoveException("Piece may not jump others " + path);
        }
      }
    }
  }

  public Position getPosition(String squareName) {
    Square s = new Square(this, squareName);
    return positions[s.x()][s.y()];
  }

  public Piece pieceAt(String squareName) {
    return getPosition(squareName).getPiece();
  }

  public Position getEnPassantCandidate() {
    return enPassantCandidate;
  }

  public void setEnPassantCandidate(Position candidate) {
    this.enPassantCandidate = candidate;
  }

}
