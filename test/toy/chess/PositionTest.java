package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-06.
 */
public class PositionTest extends TestCase {
  public void  testEquals() throws Exception {
    Board b = new Board();
    Position p = new Position(b, new Square("a1"));
    assertTrue(p.equals(p.clone()));
    assertTrue(String.valueOf(p.hashCode()), p.hashCode() > 0);

    Position p2 = new Position(b, new Square("a2"));
    assertFalse(p.equals(p2));
    assertTrue(p.hashCode() > 0);

    assertTrue(p.hashCode() != p2.hashCode());

  }

  public void testHashCode() throws Exception {
    Board b = new Board();
    Position p = new Position(b, new Square("a1"));
    assertTrue(p.equals(p.clone()));
    Position p2 = new Position(b, new Square("a2"));
    assertFalse(p.equals(p2));
  }
}
