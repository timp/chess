package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015/06/29
 */
public class BoardTest extends TestCase {

  public void testToString() {
    Board it = new Board();
    assertEquals(
        "+--------+\n" +
        "|rnbqkbnr|\n" +
        "|pppppppp|\n" +
        "|        |\n" +
        "|        |\n" +
        "|        |\n" +
        "|        |\n" +
        "|PPPPPPPP|\n" +
        "|RNBQKBNR|\n" +
        "+--------+\n",it.toString());
  }

  public void testValidateNoPieceOnSquareFrom() {
    Board it = new Board();
    try {
      it.validate(new Position(new Square("d4")), new Position(new Square("e5")));
      fail("Should have bombed");
    } catch (InvalidMoveException e) {
      e = null;
    }
  }

  public void testValidateOntopOfOwn() {
    Board it = new Board();
    it.m("a2a3").m("h7h6");
    try {
      it.m("a1a3");
      fail("Should have bombed");
    } catch (PositionOccupiedBySelfException e) {
      e = null;
    }
  }

  public void testValidateMoveToDifferentSquare() {
    Board it = new Board();
    try {
      it.m("a2a2");
      fail("Should have bombed");
    } catch (PositionOccupiedBySelfException e) {
      e = null;
    }
  }

  public void testNames() {
    Board it = new Board();
    assertEquals(
            "A1B1C1D1E1F1G1H1\n" +
            "A2B2C2D2E2F2G2H2\n" +
            "A3B3C3D3E3F3G3H3\n" +
            "A4B4C4D4E4F4G4H4\n" +
            "A5B5C5D5E5F5G5H5\n" +
            "A6B6C6D6E6F6G6H6\n" +
            "A7B7C7D7E7F7G7H7\n" +
            "A8B8C8D8E8F8G8H8\n", it.names());
  }

  public void testNothingOnPathBishop() {

  }

  public void testNothingOnPathRook() {

  }

  public void testNothingOnPathQueen() {

  }

  public void testNothingOnPathPawn() {

  }

  public void testPic() {
    assertEquals(
        "# # # # \n" +
        " # # # #\n" +
        "# # # # \n" +
        " # # # #\n" +
        "# # # # \n" +
        " # # # #\n" +
        "# # # # \n" +
        " # # # #\n", new Board().pic());
  }

  public void testPieceNames() {
    Board it = new Board();
    assertEquals("Rook", it.getPosition("a1").getPiece().getName());
    assertEquals("Knight", it.getPosition("b1").getPiece().getName());
    assertEquals("Bishop", it.getPosition("c1").getPiece().getName());
    assertEquals("Queen", it.getPosition("d1").getPiece().getName());
    assertEquals("King", it.getPosition("e1").getPiece().getName());
    assertEquals("Bishop", it.getPosition("f1").getPiece().getName());
    assertEquals("Knight", it.getPosition("g1").getPiece().getName());
    assertEquals("Rook", it.getPosition("h1").getPiece().getName());

    assertEquals("Pawn", it.getPosition("c2").getPiece().getName());

    assertEquals("Rook", it.getPosition("a8").getPiece().getName());
    assertEquals("Knight", it.getPosition("b8").getPiece().getName());
    assertEquals("Bishop", it.getPosition("c8").getPiece().getName());
    assertEquals("Queen", it.getPosition("d8").getPiece().getName());
    assertEquals("King", it.getPosition("e8").getPiece().getName());
    assertEquals("Bishop", it.getPosition("f8").getPiece().getName());
    assertEquals("Knight", it.getPosition("g8").getPiece().getName());
    assertEquals("Rook", it.getPosition("h8").getPiece().getName());
  }
}
