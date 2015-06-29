package com.megabuyte;

import static com.megabuyte.Owner.BLACK;
import static com.megabuyte.Owner.WHITE;

/**
 * @author timp
 * @since 2015/06/29
 *
 */
public class Board {

  private Position[][] positions = new Position[8][8];


  // TODO it is not nice using arrays. Use rank (1-8) and file (A-H)
  public Board() {
    // First square is black
    positions[0][0] = new Position(0, 0, new Rook(BLACK));
    positions[1][0] = new Position(0, 1, new Knight(BLACK));
    positions[2][0] = new Position(0, 2, new Bishop(BLACK));
    // King on the opposite of his queen's colour
    positions[3][0] = new Position(0, 3, new King(BLACK));
    positions[4][0] = new Position(0, 4, new Queen(BLACK));
    positions[5][0] = new Position(0, 5, new Bishop(BLACK));
    positions[6][0] = new Position(0, 6, new Knight(BLACK));
    positions[7][0] = new Position(0, 7, new Rook(BLACK));


    for (int y = 0; y < 8; y++) {
      positions[y][1] = new Position(1, y, new Pawn(BLACK));
    }

    for (int x = 2; x < 6; x++) {
      for (int y = 0; y < 8; y++) {
        positions[y][x] = new Position(x, y);
      }
    }

    for (int y = 0; y < 8; y++) {
      positions[y][6] = new Position(6, y, new Pawn(WHITE));
    }

    positions[0][7] = new Position(7, 0, new Rook(WHITE));
    positions[1][7] = new Position(7, 1, new Knight(WHITE));
    positions[2][7] = new Position(7, 2, new Bishop(WHITE));
    // King on the opposite of his queen's colour
    positions[3][7] = new Position(7, 3, new Queen(WHITE));
    positions[4][7] = new Position(7, 4, new King(WHITE));
    positions[5][7] = new Position(7, 5, new Bishop(WHITE));
    positions[6][7] = new Position(7, 6, new Knight(WHITE));
    positions[7][7] = new Position(7, 7, new Rook(WHITE));
  }
  
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

  public Board move(int[] fromCoords, int[] toCoords) {
    Position from = positions[fromCoords[0]][fromCoords[1]];
    Position to = positions[toCoords[0]][toCoords[1]];
    Path path = validate(from, to);

    // TODO Validate path

    to.setPiece(from.getPiece());
    from.setPiece(null);
    return this;
  }

  public Path validate(Position current, Position candidate) {
    if (current.getPiece() == null)
      throw new InvalidMoveException("No piece to move at " + current);

    if (candidate.getPiece() != null && (candidate.getPiece().getOwner() == current.getPiece().getOwner()) ) {
      throw new InvalidMoveException("Player already occupies " + candidate);
    }
    // TODO return path

    return new Path();
  }


}
