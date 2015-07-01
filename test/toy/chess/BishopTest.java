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
    } catch (InvalidPieceMoveException e) {
      e = null;
    }
    it.m("b2b4").m("b7b5").m("c1a3");
  }
}
