package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class KingTest extends TestCase {
  public void testCanMoveLegally() {
    Board it = new Board();
    it.m("e2e4").m("e7e5");
    it.m("e1e2").m("e8e7");
    it.m("e2d3").m("e7d6");
    it.m("d3c3").m("d6c6");
    it.m("c3d4").m("c6d5");
    it.m("d4c3").m("d5c6");
    it.m("c3d3").m("c6d6");
    it.m("d3e2").m("d6e7");
    it.m("e2e1").m("e7e8");
  }

  public void testSomeIllegalMoves() {
    Board it = new Board();
    it.m("e2e4").m("h7h6");
    it.m("e1e2").m("h6h5");
    it.m("e4e5").m("h5h4");
    try {
      it.m("e2e4");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) { }

    try {
      it.m("e2a4");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) { }
    try {
      it.m("e2g4");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) { }
    try {
      it.m("e2h7");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) { }

  }

  public void testCannotMoveIntoCheck() {
  // TODO this will be big!
  }
}
