package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class KnightTest extends TestCase {
  public void testLegalMoves() {
    Board it = new Board();
    it = it.m("b1a3").m("h7h5");
    it = it.m("a3c4").m("h5h4");

    // get king out of the way
    it = it.m("a2a3");
    it = it.m("f7f5");
    it = it.m("a3a4");
    it = it.m("e8f7");
    it = it.m("a4a5");
    it = it.m("f7g6");

    it = it.m("c4d6");
    it = it.m("h4h3");
    it = it.m("d6c4");
    it = it.m("g6h5");
    it = it.m("c4e3");

    it = it.m("g7g6");
    it = it.m("e3c4").m("g6g5");
    it = it.m("b2b4").m("g5g4");
    it = it.m("c4b2").m("g4g3");
    it = it.m("b2d3");
    it.m("f5f4");
  }
  public void testIllegalMove() {
    Board it = new Board();
    it = it.m("b1a3").m("h7h6");
    try {
      it.m("a3h5");
    } catch (InvalidPieceMoveException e) { }
  }
}
