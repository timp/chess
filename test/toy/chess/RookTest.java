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
      it.validate(it.getPosition("a1"),
          it.getPosition("b2"));
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
      e = null;
    }
    it.m("a1a3").m("h5h4").m("a3b3");
    assertEquals(
            "+--------+\n" +
            "| nbqkbnr|\n" +
            "|  pppppp|\n" +
            "| r      |\n" +
            "|pp     P|\n" +
            "|        |\n" +
            "|        |\n" +
            "|PPPPPPP |\n" +
            "|RNBQKBNR|\n" +
            "+--------+\n", it.toString());
  }

  public void testCannotMoveOntoOwnPiece() {
    Board it = new Board();
    it.m("a2a4").m("h7h6");
    try {
      it.m("a1a4");
      fail("Should have bombed");
    } catch (PositionOccupiedBySelfException e) {
      e = null;
    }
    it.m("a1a3").m("h6h5").m("a3b3");
    assertEquals(
            "+--------+\n" +
            "| nbqkbnr|\n" +
            "| ppppppp|\n" +
            "| r      |\n" +
            "|p       |\n" +
            "|       P|\n" +
            "|        |\n" +
            "|PPPPPPP |\n" +
            "|RNBQKBNR|\n" +
            "+--------+\n", it.toString());

  }
}
