package toy.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A chess board with pieces on specific squares.
 *
 * @author timp
 * @since 2015/06/29
 */
public class Board implements Cloneable {

  private Position[][] positions = null;

  private int move = 1;

  private Player firstMover;
  private Player player;
  private Player checkedPlayer;
  private Player checkmatedPlayer;
checkmatecheckmate  private Position enPassantCandidate;
  private boolean moveWasCastle;

  public Board() {
    this(new Position[8][8]);
    setup();
  }

  private Board(Position[][] positions) {
    this.positions = positions;
  }

  private void setup() {
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

  public Player getFirstMover() {
    return firstMover;
  }

  public Player getPlayer() {
    return player;
  }

  public Player getCheckedPlayer() {
    return checkedPlayer;
  }

  public Player getCheckmatedPlayer() {
    return checkmatedPlayer;
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
    return move(new MoveCode(moveCode), true);
  }

  public Board move(MoveCode fromTo, boolean checkForMate) {
    Board nextBoard = clone();
    Square fromSquare = new Square(nextBoard, fromTo.from());
    Square toSquare = new Square(nextBoard, fromTo.to());

    Position from = nextBoard.positions[fromSquare.x()][fromSquare.y()];
    Position to = nextBoard.positions[toSquare.x()][toSquare.y()];

    if (from.getPiece() == null) {
      throw new NoPieceAtPositionException(
          "No piece to move at " + from.getSquare());
    }

    System.err.println(from + ": [" + fromSquare.x() + "][" + fromSquare.y() + "]");
    if (firstMover == null) {
      nextBoard.firstMover = from.getPiece().getPlayer();
    }
    nextBoard.player = from.getPiece().getPlayer().getOpponent();

    perform(from, to);

    System.err.println("Check for check for move " + move);
    nextBoard.checkedPlayer = null;
    for (Position p : nextBoard.getOccupiedPositions()) {
      for (Position poss : p.getPiece().getPossibleMoves(p.getSquare())) {
        // Check whether possible move threatens either king
        if (poss.getPiece() instanceof King) {
          Board regicide = nextBoard.clone();
          try {
            String killerMove = p.getSquare().toString() + poss.getSquare().toString();
            regicide.move(new MoveCode(killerMove), false);
            if (!regicide.moveWasCastle) {
              System.err.println("Check from " + p + " due to " + killerMove);
              nextBoard.checkedPlayer = p.getPiece().getPlayer().getOpponent();
            }

          } catch (InvalidMoveException e) {}
        }
      }
    }

    if (checkedPlayer == null) {
      if (nextBoard.checkedPlayer != null) {
        if (nextBoard.checkedPlayer == to.getPiece().getPlayer()) {
          throw new MoveIntoCheckException("A move cannot put that player into check " + fromTo);}
        else
        {
          System.out.println(nextBoard.checkedPlayer + " in check");
          if (checkForMate) {
            System.err.println("Check for mate for move " + move);
            Board escape = nextBoard.clone();
            boolean mated = true;
            for (Position p : escape.getPlayersPositions(escape.checkedPlayer)) {
              for (Position poss : p.getPiece().getPossibleMoves(p.getSquare())) {
                try {
                  String outOfCheck = p.getSquare().toString() + poss.getSquare().toString();
                  System.err.println("Trying " + outOfCheck);
                  escape.move(new MoveCode(outOfCheck), false);
                  mated = false;
                } catch (InvalidMoveException e) {
                  System.err.println("No escape\n");
                }
              }
            }
            if (mated) {
              nextBoard.checkmatedPlayer = nextBoard.checkedPlayer;
            }
          }
        }
      }
    } else {
      if (nextBoard.checkedPlayer != null
          && nextBoard.checkedPlayer == to.getPiece().getPlayer()) {
        throw new StillInCheckException("When in check only a move out of check is allowed");
      }
    }
    return nextBoard;
  }

  public List<Position> getOccupiedPositions() {
    ArrayList<Position> them = new ArrayList<>(32);
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        Position p = positions[x][y];
        if (p.getPiece() != null) {
          them.add(p);
        }
      }
    }
    return them;
  }

  public List<Position> getPlayersPositions(Player player) {
    // this would be better as a lambda how?
    ArrayList<Position> them = new ArrayList<>(16);
    for (Position p : getOccupiedPositions()) {
      if (p.getPiece().getPlayer() == player) {
        them.add(p);
      }
    }
    return them;
  }

  public void perform(Position current, Position candidate) {
    boolean castling = false;
    if (getEnPassantCandidate() != null) {
      if (getEnPassantCandidate().getPiece() == null) {
        setEnPassantCandidate(null);
      } else {
        if (getEnPassantCandidate().getPiece().getPlayer()
            == current.getPiece().getPlayer()) {
          setEnPassantCandidate(null);
        }
      }
    }
    if (candidate.getPiece() != null
        && candidate.getPiece().getPlayer() == current.getPiece().getPlayer()) {
      if (current.getPiece() instanceof Rook &&
          candidate.getPiece() instanceof King) {

        if (current.getSquare().x() > candidate.getSquare().x()) {

          candidate.getSquare().getBoard().positions[candidate.getSquare().x() + 1][candidate.getSquare().y()]
              .setPiece(candidate.getPiece());
        } else {
          candidate.getSquare().getBoard().positions[candidate.getSquare().x() - 1][candidate.getSquare().y()]
              .setPiece(candidate.getPiece());
        }
        castling = true;
      } else {
        throw new PositionOccupiedBySelfException(
            "Player already occupies " + candidate);
      }

    }

    current.getPiece().perform(current, candidate);

    if (!(current.getPiece() instanceof Knight
        || current.getPiece() instanceof King)) {
      List<Square> path = getPath(current.getSquare(), candidate.getSquare());
      if (!path.isEmpty()) {
        if (!(castling && path.size() == 1)) {
          throw new InvalidPieceMoveException("Piece may not jump others " + path);
        }
      }
    }

    moveWasCastle = castling;
    candidate.setPiece(current.getPiece());
    current.setPiece(null);
    if (candidate.getPiece() instanceof Pawn) {
      if (candidate.getSquare().y() == 0 || candidate.getSquare().y() == 7) {
        candidate.setPiece(new Queen(candidate.getPiece().getPlayer()));
      }
    }


  }

  public List<Square> getPath(Square from, Square to) {
    ArrayList<Square> path = new ArrayList<Square>();
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
      } else if ((from.x() < to.x()) && (from.y() > to.y())) {
        // SE
        for (int x = from.x() + 1, y = from.y() - 1;
             x < to.x(); x++, y--) {
          if (positions[x][y].getPiece() != null) {
            path.add(positions[x][y].getSquare());
          }
        }
      } else if ((from.x() > to.x()) && (from.y() > to.y())) {
        for (int x = from.x() - 1, y = from.y() - 1;
             x > to.x(); x--, y--) {
          if (positions[x][y].getPiece() != null) {
            path.add(positions[x][y].getSquare());
          }
        }
      } else if ((from.x() > to.x()) && (from.y() < to.y())) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Board board = (Board) o;

    if (!Arrays.deepEquals(positions, board.positions)) {
      return false;
    }
    if (firstMover != board.firstMover) return false;
    return !(enPassantCandidate != null ? !enPassantCandidate.equals(board.enPassantCandidate) : board.enPassantCandidate != null);

  }

  @Override
  public int hashCode() {
    int result = Arrays.deepHashCode(positions);
    result = 31 * result + (firstMover != null ? firstMover.ordinal() : 0);
    result = 31 * result + (player != null ? player.ordinal() : 0);
    result = 31 * result + (enPassantCandidate != null ? enPassantCandidate.hashCode() : 0);
    return result;
  }

  @Override
  public Board clone() {
    Position[][] newPositions = new Position[8][8];
    Board newBoard = new Board(newPositions);
    newBoard.move = move + 1;
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        newPositions[x][y] = (Position) positions[x][y].clone();
        newPositions[x][y].getSquare().board = newBoard;
      }
    }
    newBoard.firstMover = firstMover;
    newBoard.player = player;
    newBoard.checkedPlayer = checkedPlayer;
    newBoard.checkmatedPlayer = checkmatedPlayer;
    newBoard.moveWasCastle = moveWasCastle;
    if (enPassantCandidate != null) {
      newBoard.enPassantCandidate = (Position) enPassantCandidate.clone();
    }
    return newBoard;
  }

  /**
   * Note that we need to flip the matrix to get normal chess notation.
   */
  @Override
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

  public void addIfStillOnBoard(ArrayList<Position> moves, int x, int y) {
    try {
      new Square(this, x, y);
      moves.add(positions[x][y]);
    } catch (InvalidChessCoordinateException ignore) {
    }
  }

}
