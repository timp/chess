package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class PawnTest extends TestCase {
  public void testValidateDiagonalToEmptyFails() {
    Board it = new Board();
    try {
      it.m("b2c3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
      e = null;
    }
  }

  public void testValidateDiagonalTake() {
    Board it = new Board();
    it.m("a2a4");
    it.m("b7b5");
    it.m("a4b5");
  }

  public void testEnPassant() {
  // TODO
  }
}
