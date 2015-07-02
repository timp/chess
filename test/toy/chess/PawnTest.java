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


  public void testMoreThanOneHorizontalFail() {
    Board it = new Board();
    it.m("a2a4").m("g7g5");
    try {
      it.m("a4g5");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
  }

  public void testEnPassant() {
    Board it = new Board();
    it.m("b2b4").m("h7h5");
    it.m("b4b5").m("c7c5");
    it.m("b5c6"); // Capture en passant
    it.m("h5h4");
    it.m("g2g4");
    it.m("h4g3"); // Capture en passant
    it.m("d2d3").m("d7d6");
  }

  public void testQueening() {
  // TODO
  }
}
