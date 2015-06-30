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
