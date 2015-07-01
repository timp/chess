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
}
