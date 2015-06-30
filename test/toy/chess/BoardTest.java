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
    it.m("b1c1").m("g8f8");
    try {
      it.validate(new Position(new Square("b2")),
          new Position(new Square("c1")));
      fail("Should have bombed");
    } catch (InvalidMoveException e) {
      e = null;
    }
  }

  public void testNames() {
    Board it = new Board();
    assertEquals(
        "A1A2A3A4A5A6A7A8\n" +
        "B1B2B3B4B5B6B7B8\n" +
        "C1C2C3C4C5C6C7C8\n" +
        "D1D2D3D4D5D6D7D8\n" +
        "E1E2E3E4E5E6E7E8\n" +
        "F1F2F3F4F5F6F7F8\n" +
        "G1G2G3G4G5G6G7G8\n" +
        "H1H2H3H4H5H6H7H8\n", it.names());
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
