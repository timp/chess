package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class SquareTest extends TestCase {
  public void testCoods() {
    Square it = new Square(1,1);
    assertEquals("B2", it.toString());
  }
}
