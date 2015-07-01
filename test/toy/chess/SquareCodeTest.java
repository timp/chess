package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class SquareCodeTest extends TestCase {
  /** This usage does not happen in the current production code. */
  public void testLongCode() {
     try {
       new SquareCode("a11");
       fail("Should have bombed");
     } catch (CodeFormatException e) { }
  }
}
