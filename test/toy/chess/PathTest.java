package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class PathTest extends TestCase {

  public void testPath() {
    Path it = new Path();
    assertEquals(0, it.size());
  }
}
