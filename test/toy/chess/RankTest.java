package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class RankTest extends TestCase {
  public void testRankToString() {
    assertEquals("A", Rank.A.toString());
  }

  public void testGetCoord() {
    assertEquals(2, Rank.C.getCoord());
  }

}
