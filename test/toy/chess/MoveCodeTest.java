package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class MoveCodeTest extends TestCase {
  public void testInvalidSquareCode() {
    try {
      new MoveCode("a1b11");
    } catch (CodeFormatException e) { }
  }
}
