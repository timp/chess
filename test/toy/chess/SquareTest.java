package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class SquareTest extends TestCase {
  public void testSquare() {
    Square it = new Square("b2");
    assertEquals("B2", it.toString());
  }

  public void testHashCode() {
    assertEquals(0, new Square("a1").hashCode());
    assertEquals(32, new Square("b2").hashCode());
    assertEquals(224, new Square("h8").hashCode());
  }
}
