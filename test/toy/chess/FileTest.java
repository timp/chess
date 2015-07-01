package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class FileTest extends TestCase {

  public void testToString() {
    assertEquals("A", File.A.toString());
  }

  public void testGetCoord() {
    assertEquals(5, File.F.getCoord());
  }

}
