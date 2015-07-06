package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class RookTest extends TestCase {
  public void testName() {
    assertEquals("Rook", new Rook(Player.BLACK).getName());
  }
  public void testAbreviation() {
    assertEquals("R", new Rook(Player.BLACK).getAbbreviation());
  }
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
    it.m("g1h3").m("g8h6");
    it.m("e2e3").m("e7e6");
    it.m("d2d3").m("d7d6");
    it.m("f1e2").m("f8e7");
    it.m("h1e1"); //Castle
    it.m("h8e8"); //castle
    it.m("a2a4").m("a7a5");
    assertEquals(
        "  abcdefgh  \n" +
        " +--------+ \n" +
        "8|RNBQRK  |8\n" +
        "7| PP BPPP|7\n" +
        "6|   PP  N|6\n" +
        "5|P       |5\n" +
        "4|p       |4\n" +
        "3|   pp  n|3\n" +
        "2| pp bppp|2\n" +
        "1|rnbqrk  |1\n" +
        " +--------+ \n" +
        "  abcdefgh  \n", it.toString());
  }
  public void testCastlingLong() {
    Board it = new Board();
    it.m("b1a3").m("b8a6");
    it.m("b2b4").m("b7b6");
    it.m("c1b2").m("c8b7");
    it.m("e2e3").m("e7e6");
    it.m("f2f4").m("f7f5");
    it.m("d1g4");
    it.m("d8g5");
    it.m("a1e1").m("a8e8");
    it.m("g4f5").m("g5f4");
    assertEquals(
        "  abcdefgh  \n" +
        " +--------+ \n" +
        "8|   KRBNR|8\n" +
        "7|PBPP  PP|7\n" +
        "6|NP  P   |6\n" +
        "5|     q  |5\n" +
        "4| p   Q  |4\n" +
        "3|n   p   |3\n" +
        "2|pbpp  pp|2\n" +
        "1|   krbnr|1\n" +
        " +--------+ \n" +
        "  abcdefgh  \n", it.toString());
  }

  public void testPathIsEmpty() {
    Board it = new Board();
    try {
      it.m("a1a4");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }
}
