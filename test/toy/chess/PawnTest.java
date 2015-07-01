package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class PawnTest extends TestCase {
  public void testValidateDiagonalToEmptyFails() {
    Board it = new Board();
    try {
      it.validate(new Position(new Square("b1")),
          new Position(new Square("b2")));
      fail("Should have bombed");
    } catch (NoPieceAtPositionException e) {
      e = null;
    }
  }

}
