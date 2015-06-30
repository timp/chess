package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class FileTest extends TestCase {

  public void testToString() {
    assertEquals("5", File.FIVE.toString());
  }

  public void testGetCoord() {
    assertEquals(4, File.FIVE.getCoord());
  }

}
