package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class KingTest extends TestCase {
  public void testCanMoveLegally() {
    Board it = new Board();
    it = it.m("e2e4").m("e7e5");
    it = it.m("e1e2").m("e8e7");
    it = it.m("e2d3").m("h7h6");
    it = it.m("d3c3").m("h6h5");
    it = it.m("c3d4").m("h5h4");
    it = it.m("d4c3").m("h4h3");
    it = it.m("c3d3").m("g7g6");
    it = it.m("d3e2").m("g6g5");
    it = it.m("e2e1").m("g5g4");
  }
  public void testCanotnMoveIntoCheck() {
    Board it = new Board();
    it = it.m("e2e4").m("e7e5");
    it = it.m("e1e2").m("e8e7");
    it = it.m("e2d3").m("e7d6");
    it = it.m("d3c3").m("d6c6");
    it = it.m("c3d4");
    try {
      it.m("c6d5");
    } catch (MoveIntoCheckException e) {}
  }
  public void testSomeIllegalMoves() {
    Board it = new Board();
    it = it.m("e2e4").m("h7h6");
    it = it.m("e1e2").m("h6h5");
    it = it.m("e4e5").m("h5h4");
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
