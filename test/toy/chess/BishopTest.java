package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class BishopTest extends TestCase {
  public void testNonDiagonalFail() {
    Board it = new Board();
    it =it.m("c2c4").m("c7c5");
    try {
      it.m("c1c3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) { }
    it = it.m("b2b4").m("b7b5");
    try {
      // Obstructed diagonal
      it.m("c1a3");
    } catch (InvalidPieceMoveException e) {}
  }
  public void testCanMoveInAllDiagonals() {
    Board it = new Board();
    it = it.m("c2c4").m("c7c5");
    it = it.m("b2b4").m("b7b5");
    it = it.m("c1a3").m("g7g6");
    it = it.m("a3b2").m("g6g5");
    it = it.m("b2e5").m("g5g4");
    it = it.m("e5b8").m("g4g3");
  }
  public void testPathIsEmpty() {
    Board it = new Board();
    try {
      it.m("c1a3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }

  public void testHashCode() {
    Bishop it = new Bishop(Player.WHITE);
    assertEquals(98, it.hashCode());
    it = new Bishop(Player.BLACK);
    assertEquals(129, it.hashCode());
  }

}
