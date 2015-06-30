package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class RookTest extends TestCase {
  public void testValidateDiagonalFails() {
    Board it = new Board();
    it.m("b1d1").m("g8f8").m("b2d2");
    try {
      it.validate(new Position(new Square("a1")),
          new Position(new Square("b2")));
      fail("Should have bombed");
    } catch (InvalidMoveException e) {
      e = null;
    }
    it.m("a1c1").m("f8e8").m("c1c2");
    assertEquals(
            "+--------+\n" +
            "| nbqkbnr|\n" +
            "|  pppppp|\n" +
            "| r      |\n" +
            "|pp      |\n" +
            "|       P|\n" +
            "|        |\n" +
            "|PPPPPPP |\n" +
            "|RNBQKBNR|\n" +
            "+--------+\n", it.toString());
  }

}
