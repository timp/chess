package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class QueenTest extends TestCase {
  public void testValidMoves() {
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    it.m("d1a4").m("h7h6");
    it.m("a4b5").m("h6h5");
    it.m("b5c5").m("g7g6");
    it.m("c5c8").m("g6g5");
  }
  public void testInvalidMove(){
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    it.m("d1b3").m("h7h6");
    try {
      it.m("b3h4");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }
}
