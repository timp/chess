package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class BishopTest extends TestCase {
  public void testNonDiagonalFail() {
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    try {
      it.m("c1c3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
    it.m("b2b4").m("b7b5").m("c1a3");
  }
  public void testCanMoveInAllDiagonals() {
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    it.m("b2b4").m("b7b5");
    it.m("c1a3").m("g7g6");
    it.m("a3b2").m("g6g5");
    it.m("b2e5").m("g5g4");
    it.m("e5b8").m("g4g3");
  }
}
