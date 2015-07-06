package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-06.
 */
public class PositionTest extends TestCase {
  public void  testEquals() throws Exception {
    Board b = new Board();
    Position p = new Position(new Square(b, "a1"));
    assertTrue(p.equals(p.clone()));
    assertEquals(0, p.hashCode());

    Position p2 = new Position(new Square(b, "a2"));
    assertFalse(p.equals(p2));
    assertEquals(961, p2.hashCode());

    assertTrue(p.hashCode() != p2.hashCode());

  }

  public void testHashCode() throws Exception {
    Board b = new Board();
    Position p = new Position(new Square(b, "a1"));
    assertTrue(p.equals(p.clone()));
    Position p2 = new Position(new Square(b, "a2"));
    assertFalse(p.equals(p2));
  }
}
