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

  private int moveNumber = 0;

  private Player player;
  private Player checkedPlayer;
  private Player checkmatedPlayer;
  private Position enPassantCandidate;

  private Square castleSquare;

  public Board() {
    this(new Position[8][8]);
    setup();
  }

  private Board(Position[][] positions) {
    this.positions = positions;
  }

  private void setup() {
    // First square (a1 or 0,0) must be black and occupied by White
    positions[0][0] = new Position(this, new Square("a1"), new Rook(Player.WHITE));
    // white
    positions[1][0] = new Position(this, new Square("b1"), new Knight(Player.WHITE));
    // black
    positions[2][0] = new Position(this, new Square("c1"), new Bishop(Player.WHITE));
    // Queen on her own colour
    // white
    positions[3][0] = new Position(this, new Square("d1"), new Queen(Player.WHITE));
    positions[4][0] = new Position(this, new Square("e1"), new King(Player.WHITE));
    positions[5][0] = new Position(this, new Square("f1"), new Bishop(Player.WHITE));
    positions[6][0] = new Position(this, new Square("g1"), new Knight(Player.WHITE));
    positions[7][0] = new Position(this, new Square("h1"), new Rook(Player.WHITE));

    for (int x = 0; x < 8; x++) {
      positions[x][1] = new Position(
          this, new Square(File.names()[x] + "2"), new Pawn(Player.WHITE));
    }


    for (int y = 2; y < 6; y++) {
      for (int x = 0; x < 8; x++) {
        positions[x][y] = new Position(
            this, new Square(File.names()[x] + Rank.names()[y]));
      }
    }

    for (int x = 0; x < 8; x++) {
      positions[x][6] = new Position(
          this, new Square(File.names()[x] + "7"), new Pawn(Player.BLACK));
    }

    // white
    positions[0][7] = new Position(this, new Square("a8"), new Rook(Player.BLACK));
    // black
    positions[1][7] = new Position(this, new Square("b8"), new Knight(Player.BLACK));
    // white
    positions[2][7] = new Position(this, new Square("c8"), new Bishop(Player.BLACK));
    // black
    // queen on her own colour
    positions[3][7] = new Position(this, new Square("d8"), new Queen(Player.BLACK));
    // King on the opposite of his queen's colour
    // white
    positions[4][7] = new Position(this, new Square("e8"), new King(Player.BLACK));
    positions[5][7] = new Position(this, new Square("f8"), new Bishop(Player.BLACK));
    positions[6][7] = new Position(this, new Square("g8"), new Knight(Player.BLACK));
    positions[7][7] = new Position(this, new Square("h8"), new Rook(Player.BLACK));
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

  public Square getCastleSquare() {
    return castleSquare;
  }

  public int getMoveNumber() {
    return moveNumber;
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

    Square fromSquare = new Square(fromTo.from());
    Position from = positions[fromSquare.x()][fromSquare.y()];
    if (from.getPiece() == null) {
      throw new NoPieceAtPositionException(
          "No piece to move at " + from.getSquare());
    }

    Board nextBoard = clone();
    nextBoard.player = from.getPiece().getPlayer();

    Square toSquare = new Square(fromTo.to());
    Position to = nextBoard.positions[toSquare.x()][toSquare.y()];
    // we will be nulling the position piece during perform(moveNumber)
    from = nextBoard.positions[fromSquare.x()][fromSquare.y()];

    if (player == from.getPiece().getPlayer()) {
      if (!(from.getPiece() instanceof King &&
          castleSquare != null &&
          castleSquare.equals(to.getSquare()))) {
        throw new InvalidTurnException("Current turn is " + player.getOpponent()
            + " from " + from
            + (from.getBoard().castleSquare == null
              ? "" :
                " allowed castle square " + from.getBoard().castleSquare)
        );
      }
    }

    nextBoard.perform(from, to);

    // Check for check
    // TODO when should this not be nulled?
    nextBoard.checkedPlayer = null;
    for (Position p : nextBoard.getOccupiedPositions()) {
      for (Position poss : p.getPossibleMoves()) {
        // Check whether possible move threatens either king
        if (poss.getPiece() instanceof King
            && poss.getPiece().getPlayer() != p.getPiece().getPlayer()) {
          Board regicide = nextBoard.clone();
          regicide.nextPlayer();
          try {
            String killerMove = p.getSquare().toString() + poss.getSquare().toString();
            regicide.move(new MoveCode(killerMove), false);
            nextBoard.checkedPlayer = p.getPiece().getPlayer().getOpponent();
            System.err.println("Have set checked player to " + nextBoard.checkedPlayer);
          } catch (InvalidMoveException e) { }
        }
      }
    }

    if (checkedPlayer == null) {
      if (nextBoard.checkedPlayer != null) {
        if (nextBoard.checkedPlayer == to.getPiece().getPlayer()) {
          throw new MoveIntoCheckException(
              "A move cannot put that player into check " + fromTo);
        } else {
          if (checkForMate) {
            System.err.println("Checking for mate: " + fromTo);
            Board nextNextBoard = nextBoard.clone();

            boolean mated = true;
            for (Position p : nextNextBoard.getPlayersPositions(nextNextBoard.checkedPlayer)) {
              for (Position poss : p.getPossibleMoves()) {
                try {
                  String outOfCheck = p.getSquare().toString() + poss.getSquare().toString();
                  Board result = nextNextBoard.move(new MoveCode(outOfCheck), false);
                  if (result.checkedPlayer == null) {
                    mated = false;
                    System.err.println("There is escape: " + outOfCheck);
                    System.err.println(" " + moveNumber +
                        " escaped check - checked player is " + result.checkedPlayer);
                  }
                } catch (InvalidMoveException e) {}
              }
            }
            if (mated) {
              nextBoard.checkmatedPlayer = nextBoard.checkedPlayer;
            }
          }
        }
      }
    } else {
      System.err.println(" " + moveNumber + " " + fromTo + " " + checkedPlayer + " is still in check");

      if (checkForMate) {
        System.err.println("Checking for mate: " + fromTo);
        Board nextNextBoard = nextBoard.clone();

        boolean mated = true;
        for (Position p : nextNextBoard.getPlayersPositions(nextNextBoard.checkedPlayer)) {
          for (Position poss : p.getPossibleMoves()) {
            try {
              String outOfCheck = p.getSquare().toString() + poss.getSquare().toString();
              Board result = nextNextBoard.move(new MoveCode(outOfCheck), false);
              if (result.checkedPlayer == null) {
                mated = false;
                System.err.println("There is escape: " + outOfCheck);
                System.err.println(" " + moveNumber +
                    " escaped check: " + result.checkedPlayer);
              }
            } catch (InvalidMoveException e) {}
          }
        }
        if (mated) {
          nextBoard.checkmatedPlayer = nextBoard.checkedPlayer;
        }
      }

      if (nextBoard.checkedPlayer != null) {
        if (nextBoard.checkedPlayer == to.getPiece().getPlayer()) {
          throw new StillInCheckException("When in check only a move out of check is allowed");
        } else {
          throw new StillInCheckException("wtf When in check only a move out of check is allowed");
        }
      } else {
        nextBoard.checkmatedPlayer = null;
        System.err.println("  " + moveNumber + " How did it escape?");
      }
    }
    return nextBoard;
  }

  private void nextPlayer() {
    player = player.getOpponent();
  }

  public void perform(Position current, Position candidate) {
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
      throw new PositionOccupiedBySelfException(
          "Player already occupies " + candidate);
    }

    if (current.getPiece() instanceof Rook &&
        current.getBoard().getPosition(
            kingsStart(current.getPiece().getPlayer())).getPiece()
            != null
        &&
        current.getBoard().getPosition(
            kingsStart(current.getPiece().getPlayer())).getPiece()
            instanceof King
        &&
        current.getBoard().getPosition(
            kingsStart(current.getPiece().getPlayer())).getPiece().getPlayer()
            == current.getPiece().getPlayer()
        ) {
      // Rook and King are on their original positions,
      // NOTE this allows them to have moved, which chess does not
      if (current.getPiece().getPlayer().equals(Player.WHITE)) {
        if (current.getSquare().toString().equals("A1") &&
            candidate.getSquare().toString().equals("D1")) {
          current.getBoard().castleSquare = new Square("C1");
        } else if (current.getSquare().toString().equals("H1") &&
            candidate.getSquare().toString().equals("F1")) {
          current.getBoard().castleSquare = new Square("G1");
        }
      } else {
        if (current.getSquare().toString().equals("A8") &&
            candidate.getSquare().toString().equals("D8")) {
          current.getBoard().castleSquare = new Square("C8");
        } else if (current.getSquare().toString().equals("H8") &&
            candidate.getSquare().toString().equals("F8")) {
          current.getBoard().castleSquare = new Square("G8");
        }
      }
    }

    current.getPiece().assertIsPossible(current, candidate);

    if (!(current.getPiece() instanceof Knight
        || current.getPiece() instanceof King)) {
      List<Square> path = getPath(current.getSquare(), candidate.getSquare());
      if (!path.isEmpty()) {
        if (!(castleSquare != null && path.size() == 1)) {
          throw new InvalidPieceMoveException("Piece may not jump others " + path);
        }
      }
    }

    candidate.setPiece(current.getPiece());
    current.setPiece(null);
    if (candidate.getPiece() instanceof Pawn) {
      if (candidate.getSquare().y() == 0 || candidate.getSquare().y() == 7) {
        candidate.setPiece(new Queen(candidate.getPiece().getPlayer()));
      }
    }
    if (castleSquare != null && ! (candidate.getPiece() instanceof Rook)) {
      current.getBoard().castleSquare = null;
    }
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

  public static String kingsStart(Player player) {
    if (player == Player.WHITE) {
      return "e1";
    } else {
      return "e8";
    }
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
    Square s = new Square(squareName);
    return positions[s.x()][s.y()];
  }

  public Position getEnPassantCandidate() {
    return enPassantCandidate;
  }

  public void setEnPassantCandidate(Position candidate) {
    this.enPassantCandidate = candidate;
  }

  public void addIfStillOnBoard(ArrayList<Position> moves, int x, int y) {
    try {
      new Square(x, y);
      moves.add(positions[x][y]);
    } catch (InvalidChessCoordinateException ignore) {
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Board board = (Board) o;

    if (!Arrays.deepEquals(positions, board.positions)) {
      return false;
    }
    return !(enPassantCandidate != null ? !enPassantCandidate.equals(board.enPassantCandidate) : board.enPassantCandidate != null);

  }

  @Override
  public int hashCode() {
    int result = Arrays.deepHashCode(positions);
    result = 31 * result + (player != null ? player.ordinal() : 0);
    result = 31 * result + (enPassantCandidate != null ? enPassantCandidate.hashCode() : 0);
    return result;
  }

  @Override
  public Board clone() {
    Position[][] newPositions = new Position[8][8];
    Board newBoard = new Board(newPositions);
    newBoard.moveNumber = moveNumber + 1;
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        newPositions[x][y] = (Position) positions[x][y].clone();
        newPositions[x][y].board = newBoard;
      }
    }
    newBoard.player = player;
    newBoard.checkedPlayer = checkedPlayer;
    newBoard.checkmatedPlayer = checkmatedPlayer;
    if (castleSquare != null) {
      newBoard.castleSquare = (Square) castleSquare.clone();
    }
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

}
