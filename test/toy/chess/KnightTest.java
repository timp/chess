package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class KnightTest extends TestCase {
  public void testLegalMoves() {
    Board it = new Board();
    it.m("b1a3").m("h7h6");
    it.m("a3c4").m("h6h5");
    it.m("c4d6").m("h5h4");
    it.m("d6c4").m("h4h3");
    it.m("c4e3").m("g7g6");
    it.m("e3c4").m("g6g5");
    it.m("b2b4").m("g5g4");
    it.m("c4b2").m("g4g3");
    it.m("b2d3").m("f2f3");
  }
  public void testIllegalMove() {
    Board it = new Board();
    it.m("b1a3").m("h7h6");
    try {
      it.m("a3h5");
    } catch (InvalidPieceMoveException e) { }
  }
}
