package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class RookTest extends TestCase {
  public void testValidateDiagonalFails() {
    Board it = new Board();
    it.m("a2a4").m("h7h6").m("b2b4").m("h6h5");
    try {
      it.m("a1b2");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
    it.m("a1a3").m("h5h4").m("a3b3");
    assertEquals(
                    "  abcdefgh  \n" +
                    " +--------+ \n" +
                    "8|RNBQKBNR|8\n" +
                    "7|PPPPPPP |7\n" +
                    "6|        |6\n" +
                    "5|        |5\n" +
                    "4|pp     P|4\n" +
                    "3| r      |3\n" +
                    "2|  pppppp|2\n" +
                    "1| nbqkbnr|1\n" +
                    " +--------+ \n" +
                    "  abcdefgh  \n", it.toString());
  }

  public void testCannotMoveOntoOwnPiece() {
    Board it = new Board();
    it.m("a2a4").m("h7h6");
    try {
      it.m("a1a4");
      fail("Should have bombed");
    } catch (PositionOccupiedBySelfException e) { }
    it.m("a1a3").m("h6h5").m("a3b3");
    assertEquals(
                    "  abcdefgh  \n" +
                    " +--------+ \n" +
                    "8|RNBQKBNR|8\n" +
                    "7|PPPPPPP |7\n" +
                    "6|        |6\n" +
                    "5|       P|5\n" +
                    "4|p       |4\n" +
                    "3| r      |3\n" +
                    "2| ppppppp|2\n" +
                    "1| nbqkbnr|1\n" +
                    " +--------+ \n" +
                    "  abcdefgh  \n", it.toString());
  }

  public void testCastlingShort() {
    Board it = new Board();
    it.m("a2a4").m("h7h6");
  }
  public void testCastlingLong() {
    Board it = new Board();
    it.m("a2a4").m("h7h6");
  }

}
