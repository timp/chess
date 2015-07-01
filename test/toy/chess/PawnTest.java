package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class PawnTest extends TestCase {
  public void testDiagonalToEmptyFails() {
    Board it = new Board();
    try {
      it.m("b2c3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
  }

  public void testDiagonalTake() {
    Board it = new Board();
    it.m("a2a4");
    it.m("b7b5");
    it.m("a4b5");
  }

  public void testMayNotMoveTwoUnlessOnStartingPosition() {
    Board it = new Board();
    it.m("a2a4");
    it.m("g7g5");
    try {
      it.m("a4a6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
    try {
      it.m("a4a7");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
  }

  public void testEnPassant() {
  // TODO
  }

  public void testQueening() {
  // TODO
  }
}
